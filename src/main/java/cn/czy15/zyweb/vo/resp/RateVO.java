package cn.czy15.zyweb.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Zhuoyu Chen
 * @project zyweb
 * @date 14:22
 */
@Data
public class RateVO<T> {

    @ApiModelProperty(value = "今日排行榜列表")
    private List<T> todayList;

    @ApiModelProperty(value = "近三天排行榜列表")
    private List<T> lastThreeDayList;
}
