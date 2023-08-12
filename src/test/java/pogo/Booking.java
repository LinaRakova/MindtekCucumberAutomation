package pogo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;


}
