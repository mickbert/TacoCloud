package com.mibe.tacocloud.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;

public class Order {
    private long id;
    private LocalDateTime placedAt;
    private List<Long> tacos=new ArrayList<>();
    @NotBlank(message="Name is required")
    private String name;
    @NotBlank(message="Address is required")
    private String address;
    @NotBlank(message="City is required")
    private String city;
    @NotBlank(message="Zip is required")
    private String zip;
    @NotBlank(message="State is required")
    private String state;
    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp="^0[1-9]|1[12]/[1-9][0-9]", message="Not a valid expiration date")
    private String ccExpDate;
    @Digits(integer=3,fraction=0, message="Not a valid CCV")
    private String cvv;

    public Order() {
        placedAt =LocalDateTime.now();
        name ="";
        address ="";
        city ="";
        zip ="";
        state ="";
        ccNumber ="";
        ccExpDate ="";
        cvv ="";
    }

    public Order(String aName, String aAddress, String aCity,
                 String azip, String aState, String aCCNumber, 
                 String aCCExpDate, String aCVV) {
        placedAt =LocalDateTime.now();
        name =aName;
        address =aAddress;
        city =aCity;
        zip =azip;
        state =aState;
        ccNumber =aCCNumber;
        ccExpDate =aCCExpDate;
        cvv =aCVV;
    }

    public long getId()                              {return id;       }
    public void setId(long aId)                      {
        id =aId;          }
    public LocalDateTime getPlacedAt()               {return placedAt; }
    public void setPlacedAt(LocalDateTime aDateTime) {
        placedAt =aDateTime;}
    public void setPlacedAtToNow()                   {
        placedAt =LocalDateTime.now();}
    public String getName()                          {return name;     }
    public void setName(String aName)                {
        name =aName;      }
    public String getAddress()                       {return address;  }
    public void setAddress(String aAddress)          {
        address =aAddress;      }
    public String getCity()                          {return city;     }
    public void setCity(String aCity)                {
        city =aCity;      }
    public String getZip()                           {return zip;      }
    public void setZip(String aZip)                  {
        zip =aZip;      }
    public String getState()                         {return state;    }
    public void setState(String aState)              {
        state =aState;      }
    public String getCcNumber()                      {return ccNumber; }
    public void setCcNumber(String aCcNumber)        {
        ccNumber =aCcNumber;      }
    public String getCcExpDate()                     {return ccExpDate;}
    public void setCcExpDate(String aCcExpDate)      {
        ccExpDate =aCcExpDate;      }
    public String getCvv()                           {return cvv;      }
    public void setCvv(String aCvv)                  {
        cvv =aCvv;      }
    public List<Long> getTacos()                     {return tacos;}

    public void addTaco(long aTacoId) {
        tacos.add(aTacoId);
    }

    public String toString() {
        return "{"+ name +" "+ address +" "+ city +" "+
                zip +" "+ state +" "+ ccNumber +" "+ ccExpDate +" "+ cvv +"}";
    }

    public int hashCode() {
        return (name + address + city +
                zip + state + ccNumber + ccExpDate + cvv +" ").hashCode();
    }
}
