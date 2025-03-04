package entity;

public class Appointment
{
    private int id;
    private int dcoId;
    private String dname;
    private int ptId;
    private String pname;
    private String appointmentDate;

    public Appointment() {

    }

    public Appointment(int id, String dname, String pname, String appointmentDate) {
        this.id = id;
        this.dname = dname;
        this.pname = pname;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(int id, int dcoId, int ptId, String appointmentDate) {
        this.id = id;
        this.dcoId = dcoId;
        this.ptId = ptId;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(int dcoId, int ptId, String appointmentDate) {
        this.dcoId = dcoId;
        this.ptId = ptId;
        this.appointmentDate = appointmentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDcoId() {
        return dcoId;
    }

    public void setDcoId(int dcoId) {
        this.dcoId = dcoId;
    }

    public int getPtId() {
        return ptId;
    }

    public void setPtId(int ptId) {
        this.ptId = ptId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
