package com.mibe.tacocloud;

import java.time.LocalDateTime; 
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.CreditCardNumber;

public class Order {
	private long m_id;
	private LocalDateTime m_placedAt;
    @NotBlank(message="Name is required")
    private String m_name;
    @NotBlank(message="Address is required")
    private String m_address;
    @NotBlank(message="City is required")
    private String m_city;
    @NotBlank(message="Zip is required")
    private String m_zip;
    @NotBlank(message="State is required")
    private String m_state;
    @CreditCardNumber(message="Not a valid credit card number")
    private String m_ccNumber;
    @Pattern(regexp="^0[1-9]|1[12]/[1-9][0-9]", message="Not a valid expiration date")
    private String m_ccExpDate;
    @Digits(integer=3,fraction=0, message="Not a valid CCV")
    private String m_cvv;      

    public Order() {
    	m_placedAt=LocalDateTime.now();
        m_name="";     
        m_address="";  
        m_city="";     
        m_zip="";
        m_state="";
        m_ccNumber=""; 
        m_ccExpDate="";
        m_cvv="";
    }

    public Order(String aName, String aAddress, String aCity,
                 String azip, String aState, String aCCNumber, 
                 String aCCExpDate, String aCVV) {
    	m_placedAt=LocalDateTime.now();
        m_name=aName;     
        m_address=aAddress;  
        m_city=aCity;     
        m_zip=azip;
        m_state=aState;
        m_ccNumber=aCCNumber; 
        m_ccExpDate=aCCExpDate;
        m_cvv=aCVV;
    }

    public long getId() 			  {return m_id;       }
    public void setId(long aId)       {m_id=aId;          }
    public LocalDateTime getPlacedAt(){return m_placedAt; }
    public void setPlacedAt(LocalDateTime aDateTime) {m_placedAt=aDateTime;}
    public String getName()           {return m_name;     }
    public void setName(String aName) {m_name=aName;      }
    public String getAddress()        {return m_address;  }
    public void setAddress(String aAddress) {m_address=aAddress;      }
    public String getCity()           {return m_city;     }
    public void setCity(String aCity) {m_city=aCity;      }
    public String getZip()            {return m_zip;      }
    public void setZip(String aZip) {m_zip=aZip;      }
    public String getState()          {return m_state;    }
    public void setState(String aState) {m_state=aState;      }
    public String getCcNumber()       {return m_ccNumber; }
    public void setCcNumber(String aCcNumber) {m_ccNumber=aCcNumber;      }
    public String getCcExpDate()      {return m_ccExpDate;}
    public void setCcExpDate(String aCcExpDate) {m_ccExpDate=aCcExpDate;      }
    public String getCvv()            {return m_cvv;      }
    public void setCvv(String aCvv) {m_cvv=aCvv;      }

    public String toString() {
        return "{"+m_name+" "+m_address+" "+m_city+" "+    
            m_zip+" "+m_state+" "+m_ccNumber+" "+m_ccExpDate+" "+m_cvv+"}";
    }

    public int hashCode() {
        return (m_name+m_address+m_city+    
                m_zip+m_state+m_ccNumber+m_ccExpDate+m_cvv+" ").hashCode();      
    }
}
