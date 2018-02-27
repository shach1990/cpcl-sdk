package com.shach.cpcl;

import org.junit.Test;

import java.nio.charset.Charset;

/**
 * <br>
 *
 * @author: Shach (shacaihong@foxmail.com)
 * @date: 2018/2/2 18:39
 */
public class PrintPP_CPCLTest {

    private boolean drawBox = true;
    PrintPP_CPCL iPrinter = new PrintPP_CPCL();

    @Test
    public void testSendYTPrintData(){

        iPrinter.pageSetup(568, 1436 - 8);
        //第一联
        if (drawBox) {
            iPrinter.drawBox(2, 2 + 4 + 4, 1, 566, 256 + 128 + 168 + 128); //第一联边框
        } else {
            iPrinter.drawLine(2, 2+4+4, 680, 566, 680, false);
        }
        iPrinter.drawLine(2, 2+4+4, 240, 566, 240,false);//第一联横线1
        iPrinter.drawLine(2, 2+4+4, 384, 566, 384,false);//第一联横线2
        iPrinter.drawLine(2, 2+4+4, 552, 566-32, 552,false);//第一联横线3
        iPrinter.drawLine(2, 40+4+4, 384, 40+4+4, 680,false);//第一联竖线1，从左到右
        iPrinter.drawLine(2, 2+408+4+4, 552, 2+408+4+4, 680,false);//第一联竖线2，从左到右
        iPrinter.drawLine(2, 566 - 32, 384, 566 - 32, 680, false);//第一联竖线3，从左到右
        //二维码信息
        iPrinter.drawQrCode(2 + 160, 16, "www.yto.net.cn", 0, 2, 5);
        iPrinter.drawText(2+320, 16+8, "代收货款", 3,0, 1,false,false);
////金额
        iPrinter.drawText(2+320, 48+8+8, "金额：", 3,0,  0, false, false);
////具体金额
        iPrinter.drawText(2+8+400, 48+8+8, "0.0元", 3,0,  1, false, false);
        //目的地
        iPrinter.drawText(2 + 166 + 32, 128 + 16 + 8, "010", 6, 0, 0, false, false);
////条码
////条码字符
        iPrinter.drawText(2+96+76+32, 340, "858691130534", 3,0,  0, false, false);
////收件人
        iPrinter.drawText(2+4+4+4,384+28,32,120,"收件人",3,0,  1,false,false);
////收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8+4+4,264+128,480,32,"程远远"+" "+"18721088532"+"  "+"",3,0, 1,false,false);
////收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2+4+32+8+4+4,372+40+22,448,120,"北京北京市朝阳区 北京曹威风威风威风 为氛围分为氛围阳曲",3,0,  1,false,false);
////寄件人
        iPrinter.drawText(2+8+4+4,552+22,32,96,"寄件人",2,0,  0,false,false);
////寄件人姓名＋电话，
        iPrinter.drawText(2+4+32+8+4+4,552+8,480,24,"chenxiang"+" "+"13512345678"+"  "+"",2,0, 0,false,false);
////寄件人地址
        iPrinter.drawText(2+4+32+8+4+4,552+40,344,112,"上海市青浦区   华新镇华徐公路",2,0,  0,false,false);
////签收人
        iPrinter.drawText(2+424,552+8,"签收人：",2,0,  0,false,false);
////日期
        iPrinter.drawText(2+424,680-26,"日期：",2,0,  0,false,false);
////派件联
        iPrinter.drawText(566-32+3,384+128,32,96,"派件联",2,0,0,false,false);
////虚线
//iPrinter.drawLine(2, 2, 680+8, 566, 680+8,false);
////第二联
        if (drawBox) {
            iPrinter.drawBox(2, 2+4+4,680+16, 566, 680+16+288);//第二联边框
        } else {
            iPrinter.drawLine(2, 2+4+4, 680+16+288, 566, 680+16+288, false);
        }
        iPrinter.drawLine(2,2+4+4, 696+32, 566, 696+32,false);//第二联横线1，从左到右
        iPrinter.drawLine(2,2+4+4, 696+160, 566-32, 696+160,false);//第二联横线2，从左到右
        iPrinter.drawLine(2,2+40+4+4, 696+160+96, 566-32, 696+160+96,false);//第二联横线3，从左到右
        iPrinter.drawLine(2, 2+40+4+4, 696+32, 2+40+4+4, 696+288,false);//第二联竖线1，从左到右
        iPrinter.drawLine(2, 248+42+4+4, 696+160+96,248+42+4+4, 680+16+288,false);//第二联竖线2，从左到右
        iPrinter.drawLine(2, 566 - 32, 696 + 32, 566 - 32, 680 + 16 + 288, false);//第二联竖线3，从左到右
        //运单号+运单号
        iPrinter.drawText(2+8+4+4,696+3,"运单号："+"858691130534"+"  "+"订单号："+"DD00000014486",2,0,  0,false,false);
        //收件人
        iPrinter.drawText(2+8+4+4,696+32+16,32,96,"收件人",2,0,  0,false,false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+8+32+8+4+4,608+128,480,24,"程远远"+" "+"18721088532"+"  "+"",2,0, 0,false,false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2+8+32+8+4+4,696+32+40+2,424,80,"北京北京市朝阳区 北京曹威风威风威风 为氛围分为氛围阳曲",2,0,  0,false,false);
        iPrinter.drawText(2+8+4+4,696+160+3,32,120,"内容品名",2,0, 0,false,false);
        iPrinter.drawText(2+4+32+8+4+4,696+160+8,432,136,"0",2,0, 0,false,false);
        iPrinter.drawText(2 + 4 + 32 + 8 + 4 + 4, 696 + 160 + 96 + 4, "数量：" + "1", 2, 0, 0, false, false);
        iPrinter.drawText(2 + 410, 696 + 160 + 96 + 4, "重量：" + "0" + "kg", 2, 0, 0, false, false);
        iPrinter.drawText(566 - 32 + 3, 696 + 32 + 80, 32, 96, "收件联", 2, 0, 0, false, false);
        if (drawBox) {
            iPrinter.drawBox(2, 2 + 4 + 4, 1000, 566, 1000 + 432 - 4 - 16);//第三联边框
        } else {
            iPrinter.drawLine(2, 2 + 4 + 4, 1000 + 432 - 4 - 16 , 566, 1000 + 432 - 4 - 16, false);
        }
        iPrinter.drawLine(2,2+4+4, 1096, 566, 1096,false);//第三联横线1，从左到右
        iPrinter.drawLine(2,2+4+4, 1096+104-8, 566-32, 1096+104-8,false);//第三联横线2，从左到右
        iPrinter.drawLine(2,2+4+4, 1096+104+104-8, 566-32, 1096+104+104-8,false);//第三联横线3，从左到右
        iPrinter.drawLine(2,2+40+4+4, 1096+104+104+96+4-4-2-8-4, 566-32, 1096+104+104+96+4-4-2-8-4,false);//第三联横线4，从左到右
        iPrinter.drawLine(2, 2 + 40 + 4 + 4 - 4, 1096, 2 + 40 + 4 + 4 - 4, 1432 - 4 - 16, false);//第三联竖线1，从左到右
        iPrinter.drawLine(2, 248 + 42 + 4 + 4, 1096 + 104 + 104 + 96 - 8, 248 + 42 + 4 + 4, 1432 - 4 - 16, false);//第三联竖线2，从左到右
        iPrinter.drawLine(2, 566 - 32, 1096, 566 - 32, 1432 - 4 - 16, false);//第三联竖线3，从左到右
        iPrinter.drawBarCode(2+160, 240+16, "858691130534", 1,  0, 3, 80);
        iPrinter.drawBarCode(2 + 250 + 4, 1000 + 8, "858691130534", 1, 0, 3, 56);
        //条码数据
        iPrinter.drawText(2+312, 1008+56+4, "858691130534", 2,  0,0,false,false);
        //收件人
        iPrinter.drawText(2+8+4,1096+5,32,96,"收件人",2,0,  0,false,false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2+8+32+8+4+4,1096+8,480,24,"程远远"+" "+"18721088532"+"  "+"",2,0, 0,false,false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2+8+32+8+4+4,1096+8+24+8,456,64,"北京北京市朝阳区 北京曹威风威风威风 为氛围分为氛围阳曲",2,0,  0,false,false);
        //寄件人
        iPrinter.drawText(2+8+4+4,1096+104+5,32,96,"寄件人",2,0,  0,false,false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2+4+32+8+4+4,1096+104+8,480,24,"chenxiang"+" "+"13512345678"+"  "+"",2,0, 0,false,false);
        //寄件人地址
        iPrinter.drawText(2+4+32+8+4+4,1096+104+8+24+8,456,72,"上海市青浦区   华新镇华徐公路",2,0,  0,false,false);
        //内容品名
        iPrinter.drawText(2+8+4+4,1096+104+104+1,32,120,"内容品名",2,0, 0,false,false);
        //订单号
        //  iPrinter.drawText(2+4+32+8,1348,"订单号："+mOrderVO.getOrderNo(),2,0, 0,false,false);
        //内容品名具体
        iPrinter.drawText(2+4+32+8+4+4,1096+104+104+8,432,156,"0",2,0, 0,false,false);
        //数量
        iPrinter.drawText(2+4+32+8+4+4,1432-32+4-4-8-4,"数量："+"1",2,0, 0,false,false);
        //重量
        iPrinter.drawText(2+400,1432-32+4-4-8-4,"重量："+"0"+"kg",2,0, 0,false,false);
        //寄件联
        iPrinter.drawText(566 - 32 + 3, 1096 + 104 + 16, 32, 96, "寄件联", 2, 0, 0, false, false);

        iPrinter.print(1, 0);

        System.out.println(new String(iPrinter.getCmd(), Charset.forName("GBK")));
    }

    @Test
    public void testSendTTPrintData() {
        iPrinter.pageSetup(576 , 1460); //设置页面大小

        //派件联
        if (drawBox) {
            iPrinter.drawBox(2 , 8 , 8 , 560 , 688);    //画派件联的框
        } else {
            iPrinter.drawLine(2, 8, 688, 560, 688, true);
        }
        iPrinter.drawLine(2 , 8 , 97 , 560 , 97,true);      //第一联横线1
        iPrinter.drawLine(2 , 508 , 97 , 508 , 688,true);   //第一联右侧竖线
        iPrinter.drawLine(2 , 420 , 97 , 420 , 177,true);   //第一联短竖线
        iPrinter.drawLine(2 , 8 , 177 , 508 , 177,true);    //第一联横线2
        iPrinter.drawLine(2 , 68 , 177 , 68 , 298,true);    //第一联左侧竖线
        iPrinter.drawLine(2 , 8 , 298 , 508 , 298,true);    //第一联横线3
        iPrinter.drawLine(2 , 68 , 298 , 68 , 375,true);    //第一联左侧竖线
        iPrinter.drawLine(2 , 8 , 375 , 508 , 375,true);    //第一联横线4
        iPrinter.drawLine(2 , 8 , 554 , 508 , 554,true);    //第一联横线5
        iPrinter.drawLine(2 , 377 , 554 , 377 , 688,true);  //第一联右侧竖线2

        //省市信息
        iPrinter.drawText(352 , 24 , 216 , 81 , "浙"+"  "+"杭州" , 4 , 0 , 1 , false , false);
        //包裹来源
        iPrinter.drawText(16 , 105 , 404 , 72 , "杭州公司包" , 4 , 0 , 1 , false , false);
        //包号
        iPrinter.drawText(452 , 113 , 24 , 48 , "2" , 4 , 0 , 1 , false , false);
        //派件联
        iPrinter.drawText(522 , 340 , 24 , 72 , "派  件  联" , 2 , 0 , 0 , false , false);
        //收件
        iPrinter.drawText(26 , 213 , 24 , 48 , "收件" , 2 , 0 , 0 , false , false);
        //收件人信息（姓名 + 手机号码）
        iPrinter.drawText(76 , 185 , 432 , 24 , "吉宇  13682429075" , 2 , 0 , 1 , false , false);
        //收件人地址
        iPrinter.drawText(76 , 220 , 432 , 81 , "浙江省 杭州市 滨江区 滨盛路1505号 1706室" , 2 , 0 , 1 , false , false);
        //寄件
        iPrinter.drawText(26 , 312 , 24 , 48 , "寄件" , 2 , 0 , 0 , false , false);
        //寄件人（姓名 + 手机号码）
        iPrinter.drawText(76 , 306 , 432 , 24 , "天天  13888888888" , 2 , 0 , 0 , false , false);
        //寄件人地址
        iPrinter.drawText(76 , 330 , 432 , 37 , "福建省 厦门市 湖滨南路 111号1111室 ", 2 , 0 , 0 , false , false);
        //运单条码
        iPrinter.drawBarCode(91 , 401 , "998016450402" , 1 , 0 , 3 , 80);
        iPrinter.drawText(185 , 485 , 333 , 24 , "998016450402" , 2 , 0 , 1 , false , false);
        //说明
        iPrinter.drawText(16 , 562 , 361 , 126 , "快件送达收件人地址，经收件人或寄件人允许的代收人签字，视为送达您的签字代表您已经验收此包裹，并已确认商品信息无误，包装完好没有划痕、破损等表面质量问题。" , 1 , 0 , 0 , false , false);
        //签收人
        iPrinter.drawText(385 , 562 , 123 , 24 , "签收人:" , 2 , 0 , 0 , false , false);
        //时间
        iPrinter.drawText(385 , 612 , 123 , 94 , "时间:" , 2 , 0 , 0 , false , false);



        //收件联
        if (drawBox) {
            iPrinter.drawBox(2 , 8 , 694 , 560 , 1006);     //收件联外框
        } else {
            iPrinter.drawLine(2, 8, 1006, 560, 1006, true);
        }
        iPrinter.drawLine(2 , 8 , 749 , 560 , 749,true);    //第二联横线1
        iPrinter.drawLine(2 , 68 , 749 , 68 , 1006,true);   //第二联左侧竖线1
        iPrinter.drawLine(2 , 508 , 749 , 508 , 1006,true); //第二联右侧竖线1
        iPrinter.drawLine(2 , 68 , 749 , 68 , 852,true);    //第二联横线2
        iPrinter.drawLine(2 , 8 , 852 , 508 , 852,true);    //第二联横线3
        iPrinter.drawLine(2 , 68 , 852 , 68 , 948,true);    //第二联竖线2
        iPrinter.drawLine(2 , 68 , 948 , 508 , 948,true);   //第二联横线4
        iPrinter.drawLine(2 , 288 , 948 , 288 , 998,true);  //第二联短竖线


        //运单号和订单号
        iPrinter.drawText(36 , 709 , 504 , 24 , "运单号:"+"998016450402"+"    "+"订单号:"+"DD8016450402" , 2 , 0 , 0 , false , false);
        //收件联
        iPrinter.drawText(522 , 813 , 24 , 72 , "收  件  联" , 2 , 0 , 0 , false , false);
        //收件
        iPrinter.drawText(26 , 776 , 24 , 48 , "收件" , 2 , 0 , 0 , false , false);
        //收件人（姓名+手机号）
        iPrinter.drawText(76 , 757 , 432 , 24 , "吉宇"+"  "+"13682429075" , 2 , 0 , 1 , false , false);
        //收件人地址
        iPrinter.drawText(76 , 790 , 432 , 63 , "浙江省 杭州市 滨江区 滨盛路1505号 1706室" , 2 , 0 , 1 , false , false);
        //内容
        iPrinter.drawText(26 , 900 , 24 , 48 , "内容" , 2 , 0 , 0 , false , false);

        //内容
        iPrinter.drawText(76 , 860 , 432 , 88 , "白瓷牡丹餐具套装（经典优惠套装）" , 2 , 0 , 0 , false , false);
        //数量
        iPrinter.drawText(76 , 964 , 212 , 42 , "数量: " + "1" , 2 , 0 , 0 , false , false);
        //重量
        iPrinter.drawText(296 , 964 , 212 , 42 , "重量: " + "2.6KG" , 2 , 0 , 0 , false , false);


        //寄件联
        if (drawBox) {
            iPrinter.drawBox(2 , 8 , 1012 , 560 , 1452);    //第三联外框
        } else {
            iPrinter.drawLine(2, 8, 1452, 560, 1452, true);
        }
        iPrinter.drawLine(2 , 8 , 1115 , 560 , 1115,true);      //第三联横线1
        iPrinter.drawLine(2 , 68 , 1115 , 68 , 1452,true);      //第三联左侧竖线1
        iPrinter.drawLine(2 , 508 , 1115 , 508 , 1452,true);    //第三联右侧竖线
        iPrinter.drawLine(2 , 68 , 1115 , 68 , 1245,true);      //第三联左侧竖线2
        iPrinter.drawLine(2 , 8 , 1245 , 508 , 1245,true);      //第三联横线2
        iPrinter.drawLine(2 , 68 , 1245 , 68 , 1327,true);      //第三联左侧竖线3
        iPrinter.drawLine(2 , 8 , 1327 , 508 , 1327,true);      //第三联横线3
        iPrinter.drawLine(2 , 68 , 1327 , 68 , 1404,true);      //第三联左侧竖线4
        iPrinter.drawLine(2 , 68 , 1404 , 508 , 1404,true);     //第三联横线4
        iPrinter.drawLine(2 , 288 , 1404 , 288 , 1452,true);    //第三联竖线5


        //第三联运单号条码
        iPrinter.drawBarCode(330 , 1032 , "998016450402" , 1 , 0 , 2 , 40);
        iPrinter.drawText(369 , 1076 , 222 , 24 , "998016450402" , 2 , 0 , 1 , false , false);
        //寄件联
        iPrinter.drawText(522 , 1224 , 24 , 72 , "寄  件  联" , 2 , 0 , 0 , false , false);
        //收件
        iPrinter.drawText(26 , 1156 , 24 , 48 , "收件" , 2 , 0 , 1 , false , false);

        //收件人（姓名 + 手机号码）
        iPrinter.drawText(76 , 1123 , 432 , 24 , "吉宇  13682429075" , 2 , 0 , 1 , false , false);
        //收件地址
        iPrinter.drawText(76 , 1155 , 432 , 90 , "浙江省 杭州市 滨江区 滨盛路1505号 1706室" , 2 , 0 , 1 , false , false);

        //寄件
        iPrinter.drawText(26 , 1262 , 24 , 48 , "寄件" , 2 , 0 , 0 , false , false);

        //寄件人（姓名 + 手机号码)
        iPrinter.drawText(76 , 1253 , 432 , 18 , "天天" + "  " + "13888888888" , 2 , 0 , 0 , false , false);
        //收件地址
        iPrinter.drawText(76 , 1279 , 432 , 48 , "福建省 厦门市 湖滨南路 111号1111室 ", 2 , 0 , 0 , false , false);

        //内容
        iPrinter.drawText(26 , 1365 , 24 , 48 , "内容" , 2 , 0 , 0 , false , false);

        //内容
        iPrinter.drawText(76 , 1335 , 432 , 69 , "白瓷牡丹餐具套装（经典优惠套装）" , 2 , 0 , 0 , false , false);

        //数量
        iPrinter.drawText(76 , 1416 , 212 , 40 , "数量: " + "1" , 2 , 0 , 0 , false , false);
        //重量
        iPrinter.drawText(296 , 1416 , 212 , 40 , "重量: " + "2.6KG" , 2 , 0 , 0 , false , false);

        iPrinter.print(1, 0);

        System.out.println(new String(iPrinter.getCmd(), Charset.forName("GBK")));
    }

}