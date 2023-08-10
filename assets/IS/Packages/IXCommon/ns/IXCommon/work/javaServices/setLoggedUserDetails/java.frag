<?xml version="1.0" encoding="UTF-8"?>

<Values version="2.0">
  <value name="name">setLoggedUserDetails</value>
  <array name="sig" type="value" depth="1">
    <value>[i] record:0:required input</value>
    <value>[i] - field:0:required fullName</value>
    <value>[i] - field:0:required userId</value>
    <value>[i] - field:0:required applicantId</value>
    <value>[i] - record:1:required linkedTradeLicences</value>
    <value>[i] -- field:0:required applicantId</value>
    <value>[i] -- field:0:required applicantLogo</value>
    <value>[i] -- field:0:required tradeLicenseNo</value>
    <value>[i] - field:0:required emiratesId</value>
    <value>[i] - field:0:required profilePicture</value>
    <value>[i] - field:0:required userType</value>
    <value>[i] - field:1:required listRoles</value>
    <value>[o] record:0:required output</value>
    <value>[o] - field:0:required fullName</value>
    <value>[o] - field:0:required userId</value>
    <value>[o] - field:0:required applicantId</value>
    <value>[o] - record:1:required linkedTradeLicences</value>
    <value>[o] -- field:0:required applicantId</value>
    <value>[o] -- field:0:required applicantLogo</value>
    <value>[o] -- field:0:required tradeLicenseNo</value>
    <value>[o] - field:0:required emiratesId</value>
    <value>[o] - field:0:required profilePicture</value>
    <value>[o] - field:0:required userType</value>
    <value>[o] - field:1:required listRoles</value>
  </array>
  <value name="sigtype">java 3.5</value>
  <value name="encodeutf8">true</value>
  <value name="body">Ly8gR2V0IHRoZSBzZXNzaW9uIG9iamVjdA0KU2Vzc2lvbiBzZXNzaW9uID0gU2VydmljZS5nZXRT
ZXNzaW9uKCk7DQogLy8gU3RhcnQgYSB0cmFuc2FjdGlvbiAgDQoNCiAgLy8gR2V0IHRoZSBJRGF0
YUN1cnNvciB0byBhY2Nlc3MgaW5wdXQgZG9jdW1lbnQgdmFyaWFibGVzDQoNCiBJRGF0YUN1cnNv
ciBpbnB1dEN1cnNvciA9IHBpcGVsaW5lLmdldEN1cnNvcigpOw0KIElEYXRhCWlucHV0ID0gSURh
dGFVdGlsLmdldElEYXRhKCBpbnB1dEN1cnNvciwgImlucHV0IiApOwkNCiAgDQogIC8vIFNldCBz
ZXNzaW9uIHZhcmlhYmxlcyBmcm9tIHRoZSBpbnB1dCBkb2N1bWVudA0KICBTdHJpbmcgZnVsbE5h
bWUgPSBJRGF0YVV0aWwuZ2V0U3RyaW5nKGlucHV0Q3Vyc29yLCAiZnVsbE5hbWUiKTsNCiAgU3Ry
aW5nIHVzZXJJZCA9IElEYXRhVXRpbC5nZXRTdHJpbmcoaW5wdXRDdXJzb3IsICJ1c2VySWQiKTsN
CiAgU3RyaW5nIGFwcGxpY2FudElkID0gSURhdGFVdGlsLmdldFN0cmluZyhpbnB1dEN1cnNvciwg
ImFwcGxpY2FudElkIik7DQogIFN0cmluZyBlbWlyYXRlc0lkID0gSURhdGFVdGlsLmdldFN0cmlu
ZyhpbnB1dEN1cnNvciwgImVtaXJhdGVzSWQiKTsNCiAgU3RyaW5nIHByb2ZpbGVQaWN0dXJlID0g
SURhdGFVdGlsLmdldFN0cmluZyhpbnB1dEN1cnNvciwgInByb2ZpbGVQaWN0dXJlIik7DQogIA0K
ICAvLyBTZXQgc2Vzc2lvbiB2YXJpYWJsZXMgZm9yIGZ1bGxOYW1lLCB1c2VySWQsIGFwcGxpY2Fu
dElkLCBlbWlyYXRlc0lkLCBwcm9maWxlUGljdHVyZQ0KICBzZXNzaW9uLnB1dCgiZnVsbE5hbWUi
LCBmdWxsTmFtZSk7DQogIHNlc3Npb24ucHV0KCJ1c2VySWQiLCB1c2VySWQpOw0KICBzZXNzaW9u
LnB1dCgiYXBwbGljYW50SWQiLCBhcHBsaWNhbnRJZCk7DQogIHNlc3Npb24ucHV0KCJlbWlyYXRl
c0lkIiwgZW1pcmF0ZXNJZCk7DQogIHNlc3Npb24ucHV0KCJwcm9maWxlUGljdHVyZSIsIHByb2Zp
bGVQaWN0dXJlKTsNCgkJICAvLyBTZXQgc2Vzc2lvbiB2YXJpYWJsZXMgZm9yIGxpbmtlZFRyYWRl
TGljZW5jZXMgYXJyYXkNCiAvLyBJRGF0YUFycmF5IGxpbmtlZFRyYWRlTGljZW5jZXNBcnJheSA9
IElEYXRhVXRpbC5nZXRJRGF0YUFycmF5KGlucHV0Q3Vyc29yLCAibGlua2VkVHJhZGVMaWNlbmNl
cyIpOw0KICBJRGF0YSBsaW5rZWRUcmFkZUxpY2VuY2VzQXJyYXlbXSA9IElEYXRhVXRpbC5nZXRJ
RGF0YUFycmF5KGlucHV0Q3Vyc29yLCJsaW5rZWRUcmFkZUxpY2VuY2VzIik7DQogIGlmIChsaW5r
ZWRUcmFkZUxpY2VuY2VzQXJyYXkgIT0gbnVsbCkgew0KICAgIGZvciAoaW50IGkgPSAwOyBpIDwg
bGlua2VkVHJhZGVMaWNlbmNlc0FycmF5Lmxlbmd0aDsgaSsrKSB7DQogICAgICBJRGF0YSBsaW5r
ZWRUcmFkZUxpY2VuY2UgPSBsaW5rZWRUcmFkZUxpY2VuY2VzQXJyYXlbaV07DQogICAgICBTdHJp
bmcgdHJhZGVMaWNlbnNlTm8gPSBJRGF0YVV0aWwuZ2V0U3RyaW5nKChJRGF0YUN1cnNvcikgbGlu
a2VkVHJhZGVMaWNlbmNlLCAidHJhZGVMaWNlbnNlTm8iKTsNCiAgICAgIFN0cmluZyBhcHBsaWNh
bnRMb2dvID0gSURhdGFVdGlsLmdldFN0cmluZygoSURhdGFDdXJzb3IpIGxpbmtlZFRyYWRlTGlj
ZW5jZSwgImFwcGxpY2FudExvZ28iKTsNCiAgICAgIFN0cmluZyBsaW5rZWRBcHBsaWNhbnRJZCA9
IElEYXRhVXRpbC5nZXRTdHJpbmcoKElEYXRhQ3Vyc29yKSBsaW5rZWRUcmFkZUxpY2VuY2UsICJh
cHBsaWNhbnRJZCIpOw0KICAgICAgDQogICAgICAvL1N0cmluZyB0cmFkZUxpY2Vuc2VObyA9IElE
YXRhVXRpbC5nZXRTdHJpbmcobGlua2VkVHJhZGVMaWNlbmNlLCAidHJhZGVMaWNlbnNlTm8iKTsN
CiAgICAgIC8vU3RyaW5nIGFwcGxpY2FudExvZ28gPSBJRGF0YVV0aWwuZ2V0U3RyaW5nKGxpbmtl
ZFRyYWRlTGljZW5jZSwgImFwcGxpY2FudExvZ28iKTsNCiAgICAgIC8vU3RyaW5nIGxpbmtlZEFw
cGxpY2FudElkID0gSURhdGFVdGlsLmdldFN0cmluZyhsaW5rZWRUcmFkZUxpY2VuY2UsICJhcHBs
aWNhbnRJZCIpOw0KICAgICAgDQogICAgICAvLyBTZXQgdGhlIHNlc3Npb24gdmFyaWFibGVzIGZv
ciBsaW5rZWRUcmFkZUxpY2VuY2VzDQogICAgICBzZXNzaW9uLnB1dCgibGlua2VkVHJhZGVMaWNl
bmNlc1siICsgaSArICJdLnRyYWRlTGljZW5zZU5vIiwgdHJhZGVMaWNlbnNlTm8pOw0KICAgICAg
c2Vzc2lvbi5wdXQoImxpbmtlZFRyYWRlTGljZW5jZXNbIiArIGkgKyAiXS5hcHBsaWNhbnRMb2dv
IiwgYXBwbGljYW50TG9nbyk7DQogICAgICBzZXNzaW9uLnB1dCgibGlua2VkVHJhZGVMaWNlbmNl
c1siICsgaSArICJdLmFwcGxpY2FudElkIiwgbGlua2VkQXBwbGljYW50SWQpOw0KICAgIH0NCiAg
fQ0KICBTdHJpbmcgdXNlclR5cGUgPSBJRGF0YVV0aWwuZ2V0U3RyaW5nKGlucHV0Q3Vyc29yLCAi
dXNlclR5cGUiKTsNCiAgc2Vzc2lvbi5wdXQoInVzZXJUeXBlIiwgdXNlclR5cGUpOw0KICBTdHJp
bmdbXSBsaXN0Um9sZXMgPSBJRGF0YVV0aWwuZ2V0U3RyaW5nQXJyYXkoaW5wdXRDdXJzb3IsICJs
aXN0Um9sZXMiKTsNCiAgaWYgKGxpc3RSb2xlcyAhPSBudWxsKSB7DQogICAgICAgIGZvciAoaW50
IGkgPSAwOyBpIDwgbGlzdFJvbGVzLmxlbmd0aDsgaSsrKSB7DQogICAgICAgICAgICBzZXNzaW9u
LnB1dCgibGlzdFJvbGVzWyIgKyBpICsgIl0iLCBsaXN0Um9sZXNbaV0pOw0KICAgICAgICB9DQog
ICAgfQ0KICANCiAgaW5wdXRDdXJzb3IuZGVzdHJveSgpOw0KICANCiAgLy8gQ3JlYXRlIHRoZSBv
dXRwdXQgSURhdGEgZG9jdW1lbnQNCiAgSURhdGEJb3V0cHV0ID0gSURhdGFGYWN0b3J5LmNyZWF0
ZSgpOwkNCiAgSURhdGFDdXJzb3IgcGlwZWxpbmVDdXJzb3JfMSA9IHBpcGVsaW5lLmdldEN1cnNv
cigpOw0KICAJCSAgDQogIElEYXRhVXRpbC5jb3B5KGlucHV0LCBvdXRwdXQpOw0KICBJRGF0YVV0
aWwucHV0KCBwaXBlbGluZUN1cnNvcl8xLCAib3V0cHV0Iiwgb3V0cHV0ICk7DQogIHBpcGVsaW5l
Q3Vyc29yXzEuZGVzdHJveSgpOw0K</value>
</Values>
