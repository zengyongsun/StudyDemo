package com.example.zengyong.studydemo.strategy;

/**
 * @author : Zeyo
 * e-mail : zengyongsun@163.com
 * date   : 2019/9/4 16:03
 * desc   :
 * version: 1.0
 */
public class Price {

    public double quote(double goodsPrice, String customerType) {
        if ("普通用户".equals(customerType)) {
            return calcPriceForNormal(goodsPrice);
        } else if ("老客户".equals(customerType)) {
            return calcPriceForOld(goodsPrice);
        } else if ("大客户".equals(customerType)) {
            return calcPriceForLarge(goodsPrice);
        }
        //其余人员都是报原价
        return goodsPrice;
    }

    private double calcPriceForLarge(double goodsPrice) {
        System.out.println("对于大客户，统一折扣10%");
        return goodsPrice * (1 - 0.1);
    }

    private double calcPriceForOld(double goodsPrice) {
        System.out.println("对于老客户，统一折扣5%");
        return goodsPrice * (1 - 0.05);
    }

    private double calcPriceForNormal(double goodsPrice) {
        System.out.println("对于新客户或者是普通客户，没有折扣");
        return goodsPrice;
    }
}
