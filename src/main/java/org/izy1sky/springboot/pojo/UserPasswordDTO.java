package org.izy1sky.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPasswordDTO {
    @NotEmpty
    @JsonProperty("old_pwd")
    private String oldPwd;
    @NotEmpty
    @JsonProperty("new_pwd")
    private String newPwd;
    @NotEmpty
    @JsonProperty("re_pwd")
    private String rePwd;
}
