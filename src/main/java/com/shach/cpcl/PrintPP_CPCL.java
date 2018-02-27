//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shach.cpcl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class PrintPP_CPCL {

    public static final int STATE_NOPAPER_UNMASK = 1;
    public static final int STATE_OVERHEAT_UNMASK = 2;
    public static final int STATE_BATTERYLOW_UNMASK = 4;
    public static final int STATE_PRINTING_UNMASK = 8;
    public static final int STATE_COVEROPEN_UNMASK = 16;

    private ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public byte[] getCmd() {
        byte[] data = bos.toByteArray();
        try {
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    boolean portSendCmd(String cmd) {
        cmd = cmd + "\r\n";

        byte[] data;
        try {
            data = cmd.getBytes("GBK");
        } catch (UnsupportedEncodingException var6) {
            return false;
        }

        bos.write(data, 0, data.length);

        return true;
    }

    public void print(int horizontal, int skip) {
        String cmd;
        if (horizontal == 0) {
            cmd = "PRINT";
            this.portSendCmd(cmd);
        } else {
            cmd = "POPRINT";
            this.portSendCmd(cmd);
        }

    }

    public void pageSetup(int pageWidth, int pageHeight) {
        String cmd = "! 0 200 200 " + pageHeight + " " + "1";
        this.portSendCmd(cmd);
        cmd = "PAGE-WIDTH " + pageWidth;
        this.portSendCmd(cmd);
    }

    public void drawBox(int lineWidth, int top_left_x, int top_left_y, int bottom_right_x, int bottom_right_y) {
        if (top_left_x > 575) {
            top_left_x = 575;
        }

        if (bottom_right_x > 575) {
            bottom_right_x = 575;
        }

        String CPCLCmd = "BOX " + top_left_x + " " + top_left_y + " " + bottom_right_x + " " + bottom_right_y + " " + lineWidth;
        this.portSendCmd(CPCLCmd);
    }

    public void drawLine(int lineWidth, int start_x, int start_y, int end_x, int end_y, boolean fullline) {
        if (start_x > 575) {
            start_x = 575;
        }

        if (end_x > 575) {
            end_x = 575;
        }

        String CPCLCmd;
        if (fullline) {
            CPCLCmd = "LF " + start_x + " " + start_y + " " + end_x + " " + end_y + " " + lineWidth;
        } else {
            CPCLCmd = "LINE " + start_x + " " + start_y + " " + end_x + " " + end_y + " " + lineWidth;
        }

        this.portSendCmd(CPCLCmd);
    }

    public void drawText(int text_x, int text_y, String text, int fontSize, int rotate, int bold, boolean reverse, boolean underline) {
        String CPCLCmd;
        if (underline) {
            CPCLCmd = "UNDERLINE ON";
        } else {
            CPCLCmd = "UNDERLINE OFF";
        }

        this.portSendCmd(CPCLCmd);
        CPCLCmd = "SETBOLD " + bold;
        this.portSendCmd(CPCLCmd);
        int size = 0;
        int ex = 1;
        int ey = 1;
        byte family;
        switch(fontSize) {
        case 1:
            family = 55;
            break;
        case 2:
            family = 1;
            break;
        case 3:
            family = 4;
            break;
        case 4:
            family = 1;
            ex = 2;
            ey = 2;
            break;
        case 5:
            family = 4;
            ex = 2;
            ey = 2;
            break;
        case 6:
            family = 1;
            ex = 4;
            ey = 4;
            break;
        case 7:
            family = 4;
            ex = 3;
            ey = 3;
            break;
        default:
            family = 1;
        }

        CPCLCmd = "SETMAG " + ex + " " + ey;
        this.portSendCmd(CPCLCmd);
        switch(rotate) {
        case 1:
            if (reverse) {
                CPCLCmd = "TR90 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T90 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            }
            break;
        case 2:
            if (reverse) {
                CPCLCmd = "TR180 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T180 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            }
            break;
        case 3:
            if (reverse) {
                CPCLCmd = "TR270 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T270 " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            }
            break;
        default:
            if (reverse) {
                CPCLCmd = "TR " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "TEXT " + family + " " + size + " " + text_x + " " + text_y + " " + text;
                this.portSendCmd(CPCLCmd);
            }
        }

    }

    public void drawText(int text_x, int text_y, int width, int height, String str, int fontsize, int rotate, int bold, boolean underline, boolean reverse) {
        String CPCLCmd;
        if (underline) {
            CPCLCmd = "UNDERLINE ON";
        } else {
            CPCLCmd = "UNDERLINE OFF";
        }

        this.portSendCmd(CPCLCmd);
        CPCLCmd = "SETBOLD " + bold;
        this.portSendCmd(CPCLCmd);
        int size = 0;
        int ex = 1;
        int ey = 1;
        int Width = 0;
        byte family;
        byte Height;
        switch(fontsize) {
        case 1:
            family = 55;
            Height = 16;
            break;
        case 2:
            family = 1;
            Height = 24;
            break;
        case 3:
            family = 4;
            Height = 32;
            break;
        case 4:
            family = 1;
            Height = 48;
            ex = 2;
            ey = 2;
            break;
        case 5:
            family = 4;
            Height = 64;
            ex = 2;
            ey = 2;
            break;
        case 6:
            family = 1;
            Height = 72;
            ex = 3;
            ey = 3;
            break;
        case 7:
            family = 4;
            Height = 96;
            ex = 3;
            ey = 3;
            break;
        default:
            family = 1;
            Height = 24;
        }

        CPCLCmd = "SETMAG " + ex + " " + ey;
        this.portSendCmd(CPCLCmd);
        char[] array = str.toCharArray();
        str = "";

        for(int i = 0; i < array.length; ++i) {
            if ((char)((byte)array[i]) != array[i]) {
                Width += Height;
                if (Width > width) {
                    switch(rotate) {
                    case 1:
                        if (reverse) {
                            CPCLCmd = "TR90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    case 2:
                        if (reverse) {
                            CPCLCmd = "TR180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    case 3:
                        if (reverse) {
                            CPCLCmd = "TR270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    default:
                        if (reverse) {
                            CPCLCmd = "TR " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "TEXT " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                    }

                    text_y += Height;
                    Width = Height;
                    str = String.valueOf(array[i]);
                } else {
                    str = str + String.valueOf(array[i]);
                }
            } else {
                Width += Height / 2;
                if (Width > width) {
                    switch(rotate) {
                    case 1:
                        if (reverse) {
                            CPCLCmd = "TR90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    case 2:
                        if (reverse) {
                            CPCLCmd = "TR180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    case 3:
                        if (reverse) {
                            CPCLCmd = "TR270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "T270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                        break;
                    default:
                        if (reverse) {
                            CPCLCmd = "TR " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        } else {
                            CPCLCmd = "TEXT " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                            this.portSendCmd(CPCLCmd);
                        }
                    }

                    text_y += Height;
                    Width = Height / 2;
                    str = String.valueOf(array[i]);
                } else {
                    str = str + String.valueOf(array[i]);
                }
            }
        }

        switch(rotate) {
        case 1:
            if (reverse) {
                CPCLCmd = "TR90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T90 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            }
            break;
        case 2:
            if (reverse) {
                CPCLCmd = "TR180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T180 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            }
            break;
        case 3:
            if (reverse) {
                CPCLCmd = "TR270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "T270 " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            }
            break;
        default:
            if (reverse) {
                CPCLCmd = "TR " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            } else {
                CPCLCmd = "TEXT " + family + " " + size + " " + text_x + " " + text_y + " " + str;
                this.portSendCmd(CPCLCmd);
            }
        }

    }

    public void drawBarCode(int start_x, int start_y, String text, int type, int rotate, int linewidth, int height) {
        int radio = 2;
        String CPCLCmd = "";
        if (rotate != 0) {
            CPCLCmd = "VBARCODE 128 " + (linewidth - 1) + " " + radio + " " + height + " " + start_x + " " + start_y + " " + text;
        } else {
            CPCLCmd = "BARCODE 128 " + (linewidth - 1) + " " + radio + " " + height + " " + start_x + " " + start_y + " " + text;
        }

        this.portSendCmd(CPCLCmd);
    }

    public void drawQrCode(int start_x, int start_y, String text, int rotate, int ver, int lel) {
        String CPCLCmd = "SETQRVER " + ver;
        this.portSendCmd(CPCLCmd);
        CPCLCmd = "BARCODE QR " + start_x + " " + start_y + " M 2 " + "U " + lel + "\r\nMA," + text + "\r\nENDQR";
        this.portSendCmd(CPCLCmd);
    }

    private String printHexString(byte[] b) {
        String a = "";

        for(int i = 0; i < b.length; ++i) {
            String hex = Integer.toHexString(b[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            a = a + hex;
        }

        return a;
    }


    private int barcodeWidth(String text, BarcodeFormat format, int Width, int Height) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix temp = writer.encode(text, format, 1, 1);
        BitMatrix result = writer.encode(text, format, temp.getWidth() * Width, Height);
        int width = result.getWidth();
        int height = result.getHeight();
        int AAA = 0;
        int BBB = 0;

        int x;
        for(x = 0; x < width; ++x) {
            if (result.get(x, 0)) {
                AAA = x;
                break;
            }
        }

        for(x = 0; x < width; ++x) {
            if (result.get(width - 1 - x, 0)) {
                BBB = x;
                break;
            }
        }

        width -= AAA + BBB;
        return width;
    }

    public void drawBarCode(int start_x, int start_y, int end_x, int end_y, String text, int type, int rotate, int linewidth, int height, int direction) {
        int radio = 2;
        String CPCLCmd = "";
        BarcodeFormat barcodeFormat;
        switch(type) {
        case 0:
            barcodeFormat = BarcodeFormat.CODE_39;
            break;
        case 1:
            barcodeFormat = BarcodeFormat.CODE_128;
            break;
        case 2:
            barcodeFormat = BarcodeFormat.CODE_93;
            break;
        case 3:
            barcodeFormat = BarcodeFormat.CODABAR;
            break;
        case 4:
            barcodeFormat = BarcodeFormat.EAN_8;
            break;
        case 5:
            barcodeFormat = BarcodeFormat.EAN_13;
            break;
        case 6:
            barcodeFormat = BarcodeFormat.UPC_A;
            break;
        case 7:
            barcodeFormat = BarcodeFormat.UPC_E;
            break;
        case 8:
            barcodeFormat = BarcodeFormat.ITF;
            break;
        default:
            barcodeFormat = BarcodeFormat.CODE_128;
        }

        try {
            int Width = this.barcodeWidth(text, barcodeFormat, linewidth, height);
            int MaxWidth = end_x - start_x;
            int MaxHeight = end_y - start_y;
            switch(direction) {
            case 1:
                if (rotate == 0) {
                    if (Width < MaxWidth) {
                        start_x += (MaxWidth - Width) / 2;
                    }
                } else if (Width < MaxHeight) {
                    start_y += (MaxHeight - Width) / 2;
                }
                break;
            case 2:
                if (rotate == 0) {
                    if (Width < MaxWidth) {
                        start_x += MaxWidth - Width;
                    }
                } else if (Width < MaxWidth) {
                    start_y += MaxHeight - Width;
                }
            }
        } catch (WriterException var17) {
            var17.printStackTrace();
        }

        if (rotate != 0) {
            CPCLCmd = "VBARCODE 128 " + (linewidth - 1) + " " + radio + " " + height + " " + start_x + " " + start_y + " " + text;
        } else {
            CPCLCmd = "BARCODE 128 " + (linewidth - 1) + " " + radio + " " + height + " " + start_x + " " + start_y + " " + text;
        }

        this.portSendCmd(CPCLCmd);
    }

}
