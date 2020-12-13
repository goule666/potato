package com.goule666.potato.proxy;

/**
 * @author wenlongnie
 * @date 2020-12-13 18:45
 * @description
 **/
public class TransportDTO {
    private String className;
    private String methodName;
    private String args;
    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
