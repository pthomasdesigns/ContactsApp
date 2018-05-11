# ContactsApp
This project shows a sample cross-platform mobile SDK for managing contacts

This project has 3 parts
1. A static C++ library that is common for iOS and Android. The source code is in ContactsApp/libcontactandroid/src/main/cpp/
2. A shared Android library that has the SDK API, JNI interface and the static library.
  The JNI interface source code is in ContactsApp/libcontactandroid/src/main/cpp/jni/
  The SDK API source code is in ContactsApp/libcontactandroid/src/main/java/com/pthomasdesigns/libcontactandroid/
3. A sample Android app that shows the usage of the SDK APIs. 
  The source code is in ContactsApp/app/src/main/java/com/pthomasdesigns/contactsapp/
## Contact SDK
The SDK has the following APIs
```java
public class ContactSdk {
  public static ContactSdk initialize();
  public void shutdown();
  public void getAllContacts(GetAllContactsListener listener);
  public void addContact(Contact contact, AddContactListener listener);
  public void setUpdateContactListener(UpdateContactListener listener);
  
  public interface AddContactListener {
        void onContactAdded(Contact contact);
  }
  public interface UpdateContactListener {
        void onContactUpdated(Contact newContact, Contact oldContact);
  }
  public interface GetAllContactsListener {
        void onSuccess(List<Contact> allContacts);
  }
}
```
## How to build   
This project uses the Gradle build system. To build this project, use "Import Project" in Android Studio.

## Sample App
1. The sample app list all contacts.
2. You can add a new contact.
3. It updates a random contact every 10 secs.
