//
// Created by TTHOMAS on 5/8/2018.
//

#ifndef CONTACTSAPP_CONTACT_H
#define CONTACTSAPP_CONTACT_H


#include <jni.h>
#include <string>

class Contact {
public:
    Contact() : valid (false) { }
    Contact(const char* firstName, const char* lastName, const char* phoneNumber);

    std::string getFirstName() const { return firstName; }
    std::string getLastName() const { return lastName; }
    std::string getPhoneNumber() const { return phoneNumber; }

    void setFirstName(std::string firstName) { this->firstName = firstName; }
    void setLastName(std::string lastName) { this->lastName = lastName; }
    void setPhoneNumber(std::string phoneNumber) { this->phoneNumber =  phoneNumber; }

    void setValid() { valid = true; }
    bool isValid() { return valid; }

private:
    std::string firstName;
    std::string lastName;
    std::string phoneNumber;

   bool valid;
};

#endif //CONTACTSAPP_CONTACT_H
