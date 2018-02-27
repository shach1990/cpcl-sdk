package com.shach.cpcl;

import javax.usb.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsbHighPos {

    private UsbPipe usbPipe;
    private UsbInterface iface;

    public UsbHighPos() {

    }

    public void claimDevice(short vendor, short produkt) throws UsbException {
        findDevice(vendor, produkt);
    }

    public void releaseUsb() {
        try {
            this.usbPipe.close();
        } catch (UsbException e) {
            try {
                iface.release();
            } catch (UsbException e1) {

            }
        }


    }

    public static void main(String[] args) throws UsbException {

        UsbHighPos upos = new UsbHighPos();

        upos.claimDevice((short) (0x09C5), (short) (0x0386));

        String text = "! 0 200 200 700 1\r\n" +
                "PAGE-WIDTH 450\r\n" +
                "RIGHT\r\n" +
                "TEXT 1 0 0 8 3 - 1\r\n" +
                "CENTER\r\n" +
                "B QR 0 30 M 2 U 8 MA, 123212456456446546\r\n" +
                "ENDQR\r\n" +
                "SETBOLD 1\r\n" +
                "TEXT 1 0 0 220 123212456456446546\r\n" +
                "LEFT\r\n" +
                "TEXT 0 3 0 320 广州\r\n" +
                "SETBOLD 0\r\n" +
                "CENTER\r\n" +
                "TEXT 0 3 0 320 --->\r\n" +
                "RIGHT\r\n" +
                "SETBOLD 1\r\n" +
                "TEXT 0 3 0 320 杭州\r\n" +
                "SETBOLD 0\r\n" +
                "CENTER\r\n" +
                "TEXT 1 3 0 420 5 - 2\r\n" +
                "ON-FEED FEED\r\n" +
                "FORM\r\n" +
                "PRINT\r\n";


        upos.print(text.getBytes(Charset.forName("GBK")));

        upos.releaseUsb();
    }


    public void findDevice(short vendorId, short productId) throws UsbException {


        List<UsbDevice> devices = UsbHostManager.getUsbServices().getRootUsbHub().getAttachedUsbDevices();
        if (devices.size() < 0) {
            throw new UsbException("Unable to get device list");
        }

        for (UsbDevice device : devices) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId) {

                UsbConfiguration configuration = device.getActiveUsbConfiguration();//获取配置信息
                this.iface = configuration.getUsbInterface((byte) 0x0);//接口
                iface.claim();

                UsbEndpoint usbEndpoint = iface.getUsbEndpoint((byte) 0x02);
                this.usbPipe = usbEndpoint.getUsbPipe();
                this.usbPipe.open();

                return ;
            }
        }

        throw new UsbException("Device not found");
    }


    public int print(byte[] data) throws UsbException {

        return usbPipe.syncSubmit(data);
    }

}
