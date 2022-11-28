package org.mortal.mtool.common.externals;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.mortal.mtool.common.basic.BREnum;
import org.mortal.mtool.common.core.annotations.ExternalService;
import org.springframework.stereotype.Service;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 16:10
 * @description 测试外部服务
 */
@Slf4j
@Service
@ExternalService
public class ShenServiceImpl implements IShenService {
    @Override
    public String regular() {
        return "This is Normal data";
    }

    @Override
    public String anomaly() {
        JSONObject object = new JSONObject();
        object.put("bookId", "100010");
        object.put("bookName", "defaultName");

        BREnum.RC_ERROR.assertNotNull(object.get("cost"));
        return "This is an abnormal piece of data";
    }
}
