package sample.controlleur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import sample.services.loginServiceImpl;
import sample.Models.login;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class loginController implements Initializable {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessagelabel;

    @FXML
    private ImageView MunicipalImageView;
    @FXML
    private TextField cinfield;
    @FXML
    private TextField PasswordField;
    @FXML
    private ImageView logImageView;


    @FXML
    private ImageView keyImageView;

    @FXML
    private ImageView userImageView;



    @FXML
    private ImageView brandingImageView;



    login log=null;
    loginServiceImpl loginService=new loginServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        File lockfile = new File("Pictures/Done.png");
        Image lockImage = new Image(lockfile.toURI().toString());
        logImageView.setImage((lockImage));
        File MunicipalFile = new File("Pictures/Municipalité.png");
        Image MunicipalImage =new Image(MunicipalFile.toURI().toString());
        MunicipalImageView.setImage(MunicipalImage);
        File keyFile = new File("Pictures/Key.png");
        Image keyImage =new Image(keyFile.toURI().toString());
       keyImageView.setImage(keyImage);
        File userFile = new File("Pictures/User.png");
        Image userImage =new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);
        File brandFile = new File("Pictures/1111.png");
        Image brandImage =new Image(brandFile.toURI().toString());
        brandingImageView.setImage(brandImage);
    }

    public void loginButtonOnAction(){

        if((cinfield.getText().isEmpty()==false)&&(PasswordField.getText().isEmpty()==false)){
            validatelogin();
        } else {
            loginMessagelabel.setText("Veuillez saisir le pseudo et le mot de passe");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void validatelogin(){
      if(loginService.verif(cinfield.getText(),PasswordField.getText())==true){
               try {
                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/10.fxml"));
                   Parent root1 = (Parent) fxmlLoader.load();
                   Stage stage = new Stage();
                   Accueilcontrolleur controlleur=fxmlLoader.getController();
                   controlleur.login(loginService.getlogin(cinfield.getText()));
                   stage.initModality(Modality.APPLICATION_MODAL);
                   stage.initStyle(StageStyle.UNDECORATED);
                   stage.setTitle("ABC");
                   stage.setScene(new Scene(root1));
                   stage.setMaximized(true);
                   stage.show();
                   Stage stage2 =(Stage) cancelButton.getScene().getWindow();
                   stage2.close();
               }catch(Exception e){
                   e.printStackTrace();
                   e.getCause();
               }
           }else {
               loginMessagelabel.setText("Authentification invalide essayer à nouveau");
           }
       }


    @FXML
    void sendpassword(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample/views/verification.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }


   /* @FXML
    void sendpassword(){
        if(!(cinfield.getText().trim().isEmpty())){
        String sql="select * from personnelinfo where cin='"+cinfield.getText()+"'";
        String email = null;
        Databasecon connectNow= new Databasecon();
        Connection connectdb =connectNow.getConnexion();
        try{
            Statement statement = connectdb.createStatement();
            ResultSet queryResult =statement.executeQuery(sql);
            if(queryResult.next()){
                email=queryResult.getString("email");
            }
        }catch(Exception e){e.printStackTrace(); }
        sendmail(email);}
        else         JOptionPane.showMessageDialog(null, "saisir votre cin");

    }

public void sendmail(String recipient){

        System.out.println("preparing to send mail");
        Properties propertie = new Properties();
        propertie.put("mail.smtp.auth","true");
        propertie.put("mail.smtp.starttls.enable","true");
        propertie.put("mail.smtp.host","smtp.gmail.com");
        propertie.put("mail.smtp.port","587");
        propertie.put("mail.debug","true");
        final String myemail="teatapplication123@gmail.com";
        final String mypassword="aze.1234";
    Session session = Session.getInstance(propertie, new Authenticator() {
@Override
        protected PasswordAuthentication getPasswordAuthentication() {

               return new javax.mail.PasswordAuthentication(myemail,mypassword);
            }

    });
    Message message=preparedmessage(session,myemail,recipient);
    try{

        Transport.send(message);
        JOptionPane.showMessageDialog(null, "check votre boite mail pour recupere votre mdp");
    }catch(Exception e){  e.printStackTrace();
    }
    }

    @SuppressWarnings("unused")
    private Message preparedmessage(Session session,String myemail,String recipient){
        Databasecon connectNow= new Databasecon();
        Connection connectdb =connectNow.getConnexion();
    String sql ="select * from personnel where cin='"+cinfield.getText()+"'";
String pass=null;
try{
    Statement statement = connectdb.createStatement();
    ResultSet queryResult =statement.executeQuery(sql);
    if(queryResult.next()){
        pass=queryResult.getString("mdp");
    }
}catch(Exception e){e.printStackTrace(); }
String text="votre mot de passe est"+pass;
String object="Recuperation de mot de passe";
Message message=new MimeMessage(session);
try{

    message.setFrom(new InternetAddress(myemail));
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    message.setSubject(object);
    String htmlcode="<h1> "+text+"</h1> <h2><b> </h2></b>";
    message.setContent(htmlcode,"text/html");

}catch(Exception e){e.printStackTrace(); }
        return message;
    }*/
}
