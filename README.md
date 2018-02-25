## complete Personalized HTML email sending system
### ver 0.8 - Dangerous sharp edges
#### 1. What is it?
Upload zip packages of html emails to be send to multiple receivers.<br>
receiver lists can be imported from json or CSV file.<br>
Emails can be personalized per recipient.
<br><br><b>Example<br></b>
In html you write <code>insert.name</code> and in send email it will be replaced with<br>
recipients name!<br> in more complez manner, this can be used to track customers<br>
by tags for example.<br>[pics]
#### 2. Features (some coming)
Email personalization before sending<br>
Automatic image upload<br>
Saveable options<br>
Compose and save receiver lists and emails<br>
Automated scheduled running
#### 3. Technology
- java spring boot
- maven
- thymeleaf
- mysql
- Jackson / JSON
- JPA
- JavaMail API
#### 4. How to
##### 4.1 full install
Ubuntu
- Create database & user
- Clone repo
- Adjust application.properties
- run start.sh
- cd target
- run java -jar filename.jar

##### 4.2 Requires
##### 4.2.1 for local image upload
- ubuntu
- Apache2
- enable userdir in apache2 [Tutorial](http://www.techytalk.info/enable-userdir-apache-module-ubuntu-debian-based-linux-distributions/)
- userdir should be home/public_html
##### 4.2.2 for remote upload
- Mysql
- Maven
- Java

##### 4.3 Use
##### 4.3.1 zip packages
<b>Max size: 5MB</b><br>
Max size can be changed in application.properties.<br>
Uploaded zip file should contain just two files, <br>
.html file to be used in email, and pictures in a folder.
- img folder (.jpg / .png / .gif only)
- .html document

HTML files and img are now stored in folder.
##### 4.3.2 Receiver lists
New lists can be made and saved within software.<br>
Uploads should be either JSON arrays or CSV files.<br>
Lists are kept in mysql database, and can be reused.
##### 4.3.3 SMTP server credentials
Are asked after uploading a zip file.<br>
These can be saved in mysql for reuse.<br>
#### 5. TODO
- Remote img upload
- More personalization
- Options page
- import CSV
- Better user guide
- More error handling
- better automated running

#### 6. Licence

--
