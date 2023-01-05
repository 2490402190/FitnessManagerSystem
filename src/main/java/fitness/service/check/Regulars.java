package fitness.service.check;

/**
 * 使用正则表达式对用户输入进行验证
 */
public class Regulars {
    //账号为8-10位，且首字不能为0
   public static boolean checkUserId(String userId){
        String regex = "[1-9]\\d{7,9}";//正则表达式
        return userId.matches(regex);
    }

    //密码为8-20位，必须包含有数字、英文
    /**
     * ^ 匹配一行的开头位置
     * (?![0-9]+$) 预测该位置后面不全是数字
     * (?![a-zA-Z]+$)预测该位置后面不全是字母
     * \w([0-9A-Za-z]的简写) {6,10} 由6-10位数字或这字母组成
     * $匹配行结尾位置
     */
    public static boolean checkPassword(String password){
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)\\w{8,20}$";//正则表达式
        return password.matches(regex);
    }

    //邮箱为QQ号开头，@qq.com结尾
    public static boolean checkEmail(String eamil){
        String regex = "^[1-9][0-9]{4,}@qq.com$";//正则表达式
        return eamil.matches(regex);
    }
    //验证码为六位数字
   public static boolean checkVerifyCode(String verifyCod){
        String regex = "\\d{6}";//正则表达式
        return verifyCod.matches(regex);
    }
}
