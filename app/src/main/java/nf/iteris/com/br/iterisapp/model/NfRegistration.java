package nf.iteris.com.br.iterisapp.model;

/**
 * Created by cardo on 12/12/2017.
 */

public class NfRegistration {
    private String number;
    private String description;
    private String dateBilling;
    private String datePayment;
    private String status;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateBilling() {
        return dateBilling;
    }

    public void setDateBilling(String dateBilling) {
        this.dateBilling = dateBilling;
    }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
