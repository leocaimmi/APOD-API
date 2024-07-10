package ControladorAPI;

public class APODClase
{
 //atributos
   private String date; // Fecha en formato YYYY-MM-DD
    private String explanation;
    private String hdurl;
    private String media_type ;
    private String service_version;
    private String title;
    private String url;
    private boolean recurso;
    private boolean estoyRepetido;
    //constructores
 public APODClase()
 {
    date = "";
    explanation= "";
    hdurl= "";
    media_type = "";
    service_version= "";
    title= "";
    url= "";
   recurso = false;
   estoyRepetido =false;
 }

 public APODClase(String date, String explanation, String hdurl, String media_type, String service_version, String title, String url) {
  this.date = date;
  this.explanation = explanation;
  this.hdurl = hdurl;
  this.media_type = media_type;
  this.service_version = service_version;
  this.title = title;
  this.url = url;
  recurso = false;
  estoyRepetido =false;
 }
 //getters y setters

 public String getDate() {
  return date;
 }

 public void setDate(String date) {
  this.date = date;
 }

 public String getExplanation() {
  return explanation;
 }

 public void setExplanation(String explanation) {
  this.explanation = explanation;
 }

 public String getHdurl() {
  return hdurl;
 }

 public void setHdurl(String hdurl) {
  this.hdurl = hdurl;
 }

 public String getMedia_type() {
  return media_type;
 }

 public void setMedia_type(String media_type) {
  this.media_type = media_type;
 }

 public String getService_version() {
  return service_version;
 }

 public void setService_version(String service_version) {
  this.service_version = service_version;
 }

 public String getTitle() {
  return title;
 }

 public void setTitle(String title) {
  this.title = title;
 }

 public String getUrl() {
  return url;
 }

 public void setUrl(String url) {
  this.url = url;
 }

 public boolean isRecurso() {
  return recurso;
 }

 public void setRecurso(boolean recurso) {
  this.recurso = recurso;
 }

 public boolean isEstoyRepetido() {
  return estoyRepetido;
 }

 public void setEstoyRepetido(boolean estoyRepetido) {
  this.estoyRepetido = estoyRepetido;
 }
 //metodos


 @Override
 public String toString() {
  return "APODClase{" +
          "date='" + date + '\'' +
          ", explanation='" + explanation + '\'' +
          ", hdurl='" + hdurl + '\'' +
          ", media_type='" + media_type + '\'' +
          ", service_version='" + service_version + '\'' +
          ", title='" + title + '\'' +
          ", url='" + url + '\'' +
          ", recurso=" + recurso +
          ", estoyRepetido=" + estoyRepetido +
          '}';
 }
}
