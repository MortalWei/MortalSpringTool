package org.mortal.mtool.common.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author mortal
 * @version 1.0
 * @contact wangwei294@h-world.com
 * @date 2022/11/28 18:06
 * @description 测试实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortalEntity implements Serializable {
    @NotEmpty(message = "id is not null or empty")
    private String id;
    @NotEmpty(message = "name is not null or empty")
    private String name;
    private String age;
    private String gender;
    private String key;
}
