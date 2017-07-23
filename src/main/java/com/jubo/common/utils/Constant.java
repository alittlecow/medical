package com.jubo.common.utils;

/**
 * 常量
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;


    /**
     * 生效
     **/
    public static final int ENABLE = 1;

    /**
     * 失效
     **/
    public static final int DISABLE = 0;

    /**
     * 菜单类型
     *
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    //角色类型
    public enum Role {
        ROLE_ADMIN(new Long(1)), ROLE_ORDINARY_USER(new Long(2)),
        ROLE_DEALER(new Long(3)), ROLE_SHOP_KEEPER(new Long(4)), ROLE_IDCARD_USER(new Long(5));
        private Long value;

        private Role(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return value;
        }
    }

    public enum GoodsType {
        //设备使用类型
        DEVICE_USE_COUNT(new Byte("1")),
        //账户充值类型
        ACCOUNT_RECHARGE_TIME(new Byte("2"));

        private Byte value;

        private GoodsType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }


    public enum PayStatus {
        //待支付
        NEED_PAY(new Integer("0")),
        //支付中
        IN_PAY(new Integer("1")),
        //支付成功
        SUCCESS(new Integer("20")),
        //支付失败
        FAIL(new Integer("21"));

        private Integer value;

        private PayStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


    public enum AccountAdjustType {
        //用户充值
        USER_RECHARGE(new Byte("0")),
        //用户消费
        USER_CONSUME(new Byte("1")),
        //分成结算
        DEALER_SETTLEMENT(new Byte("2")),
        //提现
        ENCASHMENT(new Byte("3"));

        private Byte value;

        private AccountAdjustType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }
}
