package com.opay.sdk.model.response;

import com.opay.sdk.common.Response;
import com.opay.sdk.model.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class UserUpdateResponse extends Response {

    private UserInfo data;
}
