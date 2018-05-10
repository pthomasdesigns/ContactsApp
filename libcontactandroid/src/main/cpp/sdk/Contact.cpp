//
// Created by TTHOMAS on 5/8/2018.
//

#include "Contact.h"

Contact::Contact(const char* firstName, const char* lastName, const char* phoneNumber) {
    this->firstName = firstName;
    this->lastName = lastName;
    this->phoneNumber = phoneNumber;
    valid = true;
}