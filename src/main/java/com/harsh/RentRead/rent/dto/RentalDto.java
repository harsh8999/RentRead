package com.harsh.RentRead.rent.dto;

import java.util.Date;

import com.harsh.RentRead.book.dto.BookDto;
import com.harsh.RentRead.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RentalDto {

    Long id;
    UserDto user;
    BookDto book;
    Date rentalDate;
    boolean activeStatus;

}
