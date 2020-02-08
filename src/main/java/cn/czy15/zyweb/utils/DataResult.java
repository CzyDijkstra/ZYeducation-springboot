package cn.czy15.zyweb.utils;

import cn.czy15.zyweb.exception.code.BaseResponseCode;
import cn.czy15.zyweb.exception.code.ResponseCodeInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Zhuoyu Chen
 * @date 2019/12/27 0027 - 20:51
 */
@Data
public class DataResult <T> {

    @ApiModelProperty(value = "请求响应code，0为成功，其他为失败",name="code")
    private int code=0;

    //响应客户端提示
    @ApiModelProperty(value = "相应异常码详细信息",name = "msg")
    private String msg;

    //响应客户端内容
    @ApiModelProperty(value = "需要返回的数据",name = "data")
    private T data;

    public DataResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public DataResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public DataResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public DataResult() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public DataResult(T data) {
        this.data = data;
        this.code=BaseResponseCode.SUCCESS.getCode();
        this.msg=BaseResponseCode.SUCCESS.getMsg();
    }

    public DataResult(ResponseCodeInterface responseCodeInterface) {
        this.data = null;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }

    public DataResult(ResponseCodeInterface responseCodeInterface, T data) {
        this.data = data;
        this.code = responseCodeInterface.getCode();
        this.msg = responseCodeInterface.getMsg();
    }
    /**
     * 操作成功 data为null
     *
     * @param
     * @return       cn.czy15.zyweb.utils.DataResult<T>
     * @throws
     */
    public static <T>DataResult success(){
        return new <T>DataResult();
    }
    /**
     * 操作成功 data 不为null
     *
     * @param data
     * @return       cn.czy15.zyweb.utils.DataResult<T>
     * @throws
     */
    public static <T>DataResult success(T data){
        return new <T>DataResult(data);
    }
    /**
     * 自定义 返回操作 data 可控
     * @param code
     * @param msg
     * @param data
     * @return       cn.czy15.zyweb.utils.DataResult
     * @throws
     */
    public static <T>DataResult getResult(int code,String msg,T data){
        return new <T>DataResult(code,msg,data);
    }
    /**
     *  自定义返回  data为null
     * @param code
     * @param msg
     * @return       cn.czy15.zyweb.utils.DataResult
     * @throws
     */
    public static <T>DataResult getResult(int code,String msg){
        return new <T>DataResult(code,msg);
    }
    /**
     * 自定义返回 入参一般是异常code枚举 data为空
     * @param responseCode
     * @return       cn.czy15.zyweb.utils.DataResult
     * @throws
     */
    public static <T>DataResult getResult(BaseResponseCode responseCode){
        return new <T>DataResult(responseCode);
    }
    /**
     * 自定义返回 入参一般是异常code枚举 data 可控
     * @param responseCode
     * @param data
     * @return       cn.czy15.zyweb.utils.DataResult
     * @throws
     */
    public static <T>DataResult getResult(BaseResponseCode responseCode, T data){

        return new <T>DataResult(responseCode,data);
    }
}
