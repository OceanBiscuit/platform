package com.management.platform.base.consts;


import cn.stylefeng.roses.core.util.ToolUtil;
import com.management.platform.base.enums.CommonStatus;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import static com.management.platform.base.consts.ConfigConstant.SYSTEM_CONSTANT_PREFIX;
import static cn.stylefeng.roses.core.util.ToolUtil.getTempPath;

/**
 * 系统常量的容器
 *
 * @author fengshuonan
 * @Date 2019-06-20 13:37
 */
/*
ConcurrentHashMap底层采用分段的数组+链表实现，线程安全
通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。(读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。)
Hashtable的synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术
有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁
扩容：段内扩容（段内元素超过该段对应Entry数组长度的75%触发扩容，不会对整个Map进行扩容），插入前检测需不需要扩容，有效避免无效扩容

主要是快
*/
@Slf4j
public class ConstantsContext {

    private static final String TIPS_END = "，若想忽略此提示，请在开发管理->系统配置->参数配置，设置相关参数！";

    /**
     * 所有的常量，可以增删改查
     */

    private static Map<String, Object> CONSTNTS_HOLDER = new ConcurrentHashMap<>();

    /**
     * 添加系统常量
     */
    public static void putConstant(String key, Object value) {
        if (ToolUtil.isOneEmpty(key, value)) {
            return;
        }
        CONSTNTS_HOLDER.put(key, value);
    }

    /**
     * 删除常量
     */
    public static void deleteConstant(String key) {
        if (ToolUtil.isOneEmpty(key)) {
            return;
        }

        //如果是系统常量
        if (!key.startsWith(SYSTEM_CONSTANT_PREFIX)) {
            CONSTNTS_HOLDER.remove(key);
        }
    }

    /**
     * 获取系统常量
     */
    public static Map<String, Object> getConstntsMap() {
        return CONSTNTS_HOLDER;
    }

    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOpen() {
        String gunsKaptchaOpen = (String) CONSTNTS_HOLDER.get("GUNS_KAPTCHA_OPEN");
        if (CommonStatus.ENABLE.getCode().equalsIgnoreCase(gunsKaptchaOpen)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取管理系统名称
     */
    public static String getSystemName() {
        String systemName = (String) CONSTNTS_HOLDER.get("GUNS_SYSTEM_NAME");
        if (ToolUtil.isEmpty(systemName)) {
            log.error("系统常量存在空值！常量名称：GUNS_SYSTEM_NAME，采用默认名称：Guns快速开发平台" + TIPS_END);
            return "Guns快速开发平台";
        } else {
            return systemName;
        }
    }

    /**
     * 获取管理系统名称
     */
    public static String getDefaultPassword() {
        String defaultPassword = (String) CONSTNTS_HOLDER.get("GUNS_DEFAULT_PASSWORD");
        if (ToolUtil.isEmpty(defaultPassword)) {
            log.error("系统常量存在空值！常量名称：GUNS_DEFAULT_PASSWORD，采用默认密码：111111" + TIPS_END);
            return "111111";
        } else {
            return defaultPassword;
        }
    }

    /**
     * 获取管理系统名称
     */
    public static String getOAuth2UserPrefix() {
        String oauth2Prefix = (String) CONSTNTS_HOLDER.get("GUNS_OAUTH2_PREFIX");
        if (ToolUtil.isEmpty(oauth2Prefix)) {
            log.error("系统常量存在空值！常量名称：GUNS_OAUTH2_PREFIX，采用默认值：oauth2" + TIPS_END);
            return "oauth2";
        } else {
            return oauth2Prefix;
        }
    }

    /**
     * 获取顶部导航条是否开启
     */
    public static Boolean getDefaultAdvert() {
        String gunsDefaultAdvert = (String) CONSTNTS_HOLDER.get("GUNS_DEFAULT_ADVERT");
        if (ToolUtil.isEmpty(gunsDefaultAdvert)) {
            log.error("系统常量存在空值！常量名称：GUNS_DEFAULT_ADVERT，采用默认值：true" + TIPS_END);
            return true;
        } else {
            if (CommonStatus.ENABLE.getCode().equalsIgnoreCase(gunsDefaultAdvert)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 获取系统发布的版本号（防止css和js的缓存）
     */
    public static String getReleaseVersion() {
        String systemReleaseVersion = (String) CONSTNTS_HOLDER.get("GUNS_SYSTEM_RELEASE_VERSION");
        if (ToolUtil.isEmpty(systemReleaseVersion)) {
            log.error("系统常量存在空值！常量名称：GUNS_SYSTEM_RELEASE_VERSION，采用默认值：guns" + TIPS_END);
            return ToolUtil.getRandomString(8);
        } else {
            return systemReleaseVersion;
        }
    }

    /**
     * 获取文件上传路径(用于头像和富文本编辑器)
     */
    public static String getFileUploadPath() {
        String gunsFileUploadPath = (String) CONSTNTS_HOLDER.get("GUNS_FILE_UPLOAD_PATH");
        if (ToolUtil.isEmpty(gunsFileUploadPath)) {
            log.error("系统常量存在空值！常量名称：GUNS_FILE_UPLOAD_PATH，采用默认值：系统tmp目录" + TIPS_END);
            return getTempPath();
        } else {
            //判断有没有结尾符
            if (!gunsFileUploadPath.endsWith(File.separator)) {
                gunsFileUploadPath = gunsFileUploadPath + File.separator;
            }

            //判断目录存不存在
            File file = new File(gunsFileUploadPath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (mkdirs) {
                    return gunsFileUploadPath;
                } else {
                    log.error("系统常量存在空值！常量名称：GUNS_FILE_UPLOAD_PATH，采用默认值：系统tmp目录" + TIPS_END);
                    return getTempPath();
                }
            } else {
                return gunsFileUploadPath;
            }
        }
    }

    /**
     * 用于存放bpmn文件
     */
    public static String getBpmnFileUploadPath() {
        String bpmnFileUploadPath = (String) CONSTNTS_HOLDER.get("GUNS_BPMN_FILE_UPLOAD_PATH");
        if (ToolUtil.isEmpty(bpmnFileUploadPath)) {
            log.error("系统常量存在空值！常量名称：GUNS_BPMN_FILE_UPLOAD_PATH，采用默认值：系统tmp目录" + TIPS_END);
            return getTempPath();
        } else {
            //判断有没有结尾符
            if (!bpmnFileUploadPath.endsWith(File.separator)) {
                bpmnFileUploadPath = bpmnFileUploadPath + File.separator;
            }

            //判断目录存不存在
            File file = new File(bpmnFileUploadPath);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (mkdirs) {
                    return bpmnFileUploadPath;
                } else {
                    log.error("系统常量存在空值！常量名称：GUNS_BPMN_FILE_UPLOAD_PATH，采用默认值：系统tmp目录" + TIPS_END);
                    return getTempPath();
                }
            } else {
                return bpmnFileUploadPath;
            }
        }
    }

    /**
     * 获取系统地密钥
     */
    public static String getJwtSecret() {
        String systemReleaseVersion = (String) CONSTNTS_HOLDER.get("GUNS_JWT_SECRET");
        if (ToolUtil.isEmpty(systemReleaseVersion)) {
            String randomSecret = ToolUtil.getRandomString(32);
            CONSTNTS_HOLDER.put("GUNS_JWT_SECRET", randomSecret);
            log.error("jwt密钥存在空值！常量名称：GUNS_JWT_SECRET，采用默认值：随机字符串->" + randomSecret + TIPS_END);
            return randomSecret;
        } else {
            return systemReleaseVersion;
        }
    }

    /**
     * 获取系统地密钥过期时间（单位：秒）
     */
    public static Long getJwtSecretExpireSec() {
        Long defaultSecs = 86400L;
        String systemReleaseVersion = (String) CONSTNTS_HOLDER.get("GUNS_JWT_SECRET_EXPIRE");
        if (ToolUtil.isEmpty(systemReleaseVersion)) {
            log.error("jwt密钥存在空值！常量名称：GUNS_JWT_SECRET_EXPIRE，采用默认值：1天" + TIPS_END);
            CONSTNTS_HOLDER.put("GUNS_JWT_SECRET_EXPIRE", String.valueOf(defaultSecs));
            return defaultSecs;
        } else {
            try {
                return Long.valueOf(systemReleaseVersion);
            } catch (NumberFormatException e) {
                log.error("jwt密钥过期时间不是数字！常量名称：GUNS_JWT_SECRET_EXPIRE，采用默认值：1天" + TIPS_END);
                CONSTNTS_HOLDER.put("GUNS_JWT_SECRET_EXPIRE", String.valueOf(defaultSecs));
                return defaultSecs;
            }
        }
    }

    /**
     * 获取token的header标识
     */
    public static String getTokenHeaderName() {
        String tokenHeaderName = (String) CONSTNTS_HOLDER.get("GUNS_TOKEN_HEADER_NAME");
        if (ToolUtil.isEmpty(tokenHeaderName)) {
            String defaultName = "Authorization";
            CONSTNTS_HOLDER.put("GUNS_TOKEN_HEADER_NAME", defaultName);
            log.error("获取token的header标识为空！常量名称：GUNS_TOKEN_HEADER_NAME，采用默认值：" + defaultName + TIPS_END);
            return defaultName;
        } else {
            return tokenHeaderName;
        }
    }

    /**
     * 获取租户是否开启的标识，默认是关的
     */
    public static Boolean getTenantOpen() {
        String tenantOpen = (String) CONSTNTS_HOLDER.get("GUNS_TENANT_OPEN");
        if (ToolUtil.isEmpty(tenantOpen)) {
            log.error("系统常量存在空值！常量名称：GUNS_TENANT_OPEN，采用默认值：DISABLE" + TIPS_END);
            return false;
        } else {
            return CommonStatus.ENABLE.getCode().equalsIgnoreCase(tenantOpen);
        }
    }

}
