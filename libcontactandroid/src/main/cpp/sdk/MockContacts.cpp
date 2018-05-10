//
// Created by TTHOMAS on 5/10/2018.
//

#include "MockContacts.h"

std::vector<Contact> MockContacts::contacts  = {
         {"Alexander","Bell", "+16170000001"},
         {"Thomas", "Watson", "+16170000002"},
         {"Elisha", "Gray", "+18476003599"},
         {"Antonio", "Meucci", "+17188763245"},
         {"Guglielmo", "Marconi", "+39051203222"},
         {"Samuel", "Morse", "+16172419876"},
         {"Tim", "Berners-Lee", "+44204549898"},
         {"John", "Baird", "+4408458591006"},
         {"Thomas", "Edison", "+19086575678"}};

Contact MockContacts::getContact(int num) {
    Contact contact = contacts[num];
    contacts.erase(contacts.begin() + num);
    return contact;
}