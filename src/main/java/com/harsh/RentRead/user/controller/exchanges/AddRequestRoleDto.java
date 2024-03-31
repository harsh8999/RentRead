package com.harsh.RentRead.user.controller.exchanges;

import com.harsh.RentRead.user.entity.enums.Role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddRequestRoleDto {

    Role role;

}
