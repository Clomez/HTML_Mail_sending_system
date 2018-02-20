## complete HTML email sending system
### ver 0.6 - Dangerous sharp edges
#### 1. What is it?
Upload zip packages of html emails to be send to multiple receivers.<br>
receiver lists can be imported from json or CSV file.
<br>[pics]

#### 2. Features (some coming)
Automatic image upload<br>
Saveable options<br>
Compose and save receiver lists<br>
Automated scheduled running

#### 3. Technology
- java spring boot
- thymeleaf
- mysql
- Jackson / JSON
- JPA
- JavaMail API

#### 4. How to

##### 4.1 install

Ubuntu
- Clone repo
- change mysql users & db's
- mvn package
- cd target
- run java -jar filename.jar

##### 4.2 Requires
- Mysql
- Maven
- Java

##### 4.3 Use
##### 4.3.1 zip packages
Uploaded zip file should contain just two files, <br>
.html file to be used in email, and pictures in a folder.
- img folder
- .html document

HTML files are not saved when composing email. all files will be<br>
removed after use.
##### 4.3.2 Receiver lists
New lists can be made and saved within software.<br>
Uploads should be either JSON arrays or CSV files.<br>
Lists are kept in mysql database, and can be reused.
##### 4.3.3 SMTP server credentials
Are asked after uploading a zip file.<br>
These can be saved in mysql for reuse.<br>
#### 5. TODO
- import JSON
- import CSV
- Better user guide
- More error handling
- Archive for send mails?
- better automated running

#### 6. Licence

--