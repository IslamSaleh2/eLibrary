package entities;


import java.util.Date;


public class IssueBookEntity {
    private String callno,studentid,studentname;
    private long studentmobile;
    private String returnstatus;
    private Date issueddate;

    public IssueBookEntity() {

    }

    public IssueBookEntity(String callno, String studentid, String studentname, long studentmobile) {
        this.callno = callno;
        this.studentid = studentid;
        this.studentname = studentname;
        this.studentmobile = studentmobile;
    }

    public String getCallno() {
        return callno;
    }

    public void setCallno(String callno) {
        this.callno = callno;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public long getStudentmobile() {
        return studentmobile;
    }

    public void setStudentmobile(long studentmobile) {
        this.studentmobile = studentmobile;
    }

    public String getReturnstatus() {
        return returnstatus;
    }

    public void setReturnstatus(String returnstatus) {
        this.returnstatus = returnstatus;
    }


    public Date getIssueddate() {
        return issueddate;
    }

    public void setIssueddate(Date issueddate) {
        this.issueddate =  issueddate;
    }


}
