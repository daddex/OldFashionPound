package org.daddex.oldfashionpound.model.bean;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OldUkCurrency {
    private Integer pounds;
    private Integer shillings;
    private Integer pennies;
    private Integer remainder;

    @Override
    public String toString() {
        return "OldUkCurrency{" +
                "pounds=" + pounds +
                ", shillings=" + shillings +
                ", pennies=" + pennies +
                (remainder != null ?  ", remainder=" + remainder : ", remainder=0" ) +
                '}';
    }
}
