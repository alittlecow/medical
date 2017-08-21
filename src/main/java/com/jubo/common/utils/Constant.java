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


    //role
    //分销商角色id
    public static final int DEALER = 1;


    /**
     * 生效
     **/
    public static final int ENABLE = 1;

    /**
     * 失效
     **/
    public static final int DISABLE = 0;


    //充值订单和消费订单前缀
    public static final String PRE_RECHARGE_ORDER = "1";

    public static final String PRE_CONSUMER_ORDER = "2";


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

        ROLE_ADMIN(new Long("0")),
        ROLE_PROVINCE_DEALER(new Long("1")),
        ROLE_CITY_DEALER(new Long("2")),
        ROLE_MERCHANT(new Long("3")),
        ROLE_APP_USER(new Long("6"));
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





    public enum ApplyCardStatus {
        //提交申请
        INIT(new Integer("0")),
        //审批失败
        APPLY_FAIL(new Integer("1")),
        //审批成功
        APPLY_SUCCESS(new Integer("2"));

        private Integer value;

        private ApplyCardStatus(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


    public enum AccountAdjustType {
        //用户充值
        USER_RECHARGE(new Byte("10")),
        //分成结算
        DEALER_SETTLEMENT(new Byte("11")),

        //ID卡充值
        CARD_RECHARGE(new Byte("20")),

        //使用设备
        USE_DEVICE(new Byte("21")),

        //提现
        ENCASHMENT(new Byte("22"));

        private Byte value;

        private AccountAdjustType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum OrderType {
        //消费订单表
        //ID卡充值
        ID_RECHARGE(new Byte("0")),
        //APP消费
        USE_DEVICE_ONLINE(new Byte("1")),
        //ID卡消费
        USER_DEVICE_BY_CARD(new Byte("2"));

        private Byte value;

        private OrderType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }


    public enum CommonStatus {
        //1 已经绑定
        TRUE(new Byte("1")),
        //0  没有绑定
        FALSE(new Byte("0"));

        private Byte value;

        private CommonStatus(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum CardAdjustType {
        //1 使用
        USE(new Byte("1")),
        //2  充值
        RECHARGE(new Byte("2"));

        private Byte value;

        private CardAdjustType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }


    public enum PayType {
        //支付宝
        ALIPAY(new Byte("0")),
        //微信
        WECHAT(new Byte("1")),
        //银联
        BANK(new Byte("2")),
        //个人账户
        ACCOUNT(new Byte("3")),
        //刷卡支付
        CARD(new Byte("4"));

        private Byte value;

        private PayType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum DeviceBindType {
        //0 未使用 1已经绑定 2解绑
        NEVER_USE(new Byte("0")),

        IS_BIND(new Byte("1")),

        IS_NOT_BIND(new Byte("2"));

        private Byte value;

        private DeviceBindType(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum DeviceUseStatus {
        //设备使用状态（0未使用 1使用中）
        NEVER_USE(new Byte("0")),

        USE_ING(new Byte("1"));

        private Byte value;

        private DeviceUseStatus(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }

    public enum DeviceBreakdownStatus {
        //设备是否故障（0正常 1故障）
        NORMAL(new Byte("0")),

        BREAKDOWN(new Byte("1"));

        private Byte value;

        private DeviceBreakdownStatus(Byte value) {
            this.value = value;
        }

        public Byte getValue() {
            return value;
        }
    }
}
