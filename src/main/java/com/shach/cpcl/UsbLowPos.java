package com.shach.cpcl;

import org.usb4java.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

public class UsbLowPos {

    private DeviceHandle handle;
    private Context context;
    private short vendor;
    private short product;
    private byte endpoint;

    public UsbLowPos() {
        context = new Context();
    }

    public void claimDevice(short vendor, short produkt) {
        findDevice(context, vendor, produkt);
    }

    public void releaseUsb() {

        LibUsb.releaseInterface(handle, 0);
        LibUsb.close(handle);

    }

    public static void main(String[] args) {

        UsbLowPos upos = new UsbLowPos();

        upos.claimDevice((short) (0x09C5), (short) (0x0386));
        upos.setEndpoint(LibUsb.RECIPIENT_ENDPOINT);

//        char[] initEP = new char[]{0x1b, '@'};
//        char[] cutP = new char[]{0x1d, 'V', 1};
//
//        String ptxt = new String(initEP) + 'n';
//
//        upos.print(ptxt);
//
//        upos.print("abc n");
//        upos.print("abc n");
//
//        upos.print("nnnnnnnnn");
//
//        upos.print(new String(cutP));


        String text = "! 130 200 200 381 1\r\n" +
                    "BT 2 0 0\r\n" +
                    "B 93 1 0 100 0 0 13800138003\r\n" +
                    "TEXT 1 3 0 160 地址：广东省广州市\r\n" +
                    "TEXT 1 3 0 240 数量：3-3\r\n" +
                    "GAP-SENSE\r\n" +
                    "FORM\r\n" +
                    "PRINT\r\n";

        upos.print(text.getBytes(Charset.forName("GBK")));


        upos.releaseUsb();

    }

    public void setEndpoint(byte endpoint) {
        this.endpoint = endpoint;
    }

    public byte getEndpoint() {
        return endpoint;
    }

    public void findDevice(Context context, short vendorId, short productId) {

        // Initialize the libusb context
        int result = LibUsb.init(context);
        if (result < LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to initialize libusb", result);
        }

        // Read the USB device list
        DeviceList list = new DeviceList();
        result = LibUsb.getDeviceList(context, list);
        if (result < 0) {
            throw new LibUsbException("Unable to get device list", result);
        }

        try {
            // Iterate over all devices and list them
            for (Device device : list) {

                int address = LibUsb.getDeviceAddress(device);
                int busNumber = LibUsb.getBusNumber(device);
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);

                if (result < 0) {
                    throw new LibUsbException(
                            "Unable to read device descriptor", result);
                }

                if (result != LibUsb.SUCCESS) {
                    throw new LibUsbException("Unable to read device descriptor", result);
                }
                if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId) {

                    System.out.println("Device Found");
                    getDeviceHandle(device);
                    //LibUsb.claimInterface(handle, 0);
                }

            }
        } finally {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }
    }

    public void getDeviceHandle(Device device) {

        handle = new DeviceHandle();

        int result = LibUsb.open(device, handle);

        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to open USB device", result);
        }

        try {
            // Use device handle here
            claimDevice(handle, 0);
        } finally {
            //LibUsb.close(handle);
        }
    }

    public void claimDevice(DeviceHandle handle, int interfaceNumber) {
        int result = LibUsb.claimInterface(handle, interfaceNumber);

        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to claim interface", result);
        }
        
    }

    public boolean print(byte[] data) {

        ByteBuffer buffer = ByteBuffer.allocateDirect(data.length);

        buffer.put(data);

        IntBuffer transfered = IntBuffer.allocate(3);

        int result = LibUsb.bulkTransfer(handle, endpoint, buffer, transfered, 3000);

        if (result != LibUsb.SUCCESS) {
            System.out.println("EXCEPTION THROWN: " + result);
            return false;
        }

        System.out.println(transfered.get() + " bytes sent");
        return true;
    }

}
