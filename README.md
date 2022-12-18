# Yet Another Binary Notation aka YABN

This library provides a compressed version of JSON to be stored in the smallest amount of bytes.

It has 1 stipulation and that is that keys can not contain a null byte but besides that it has the same requirements as JSON.

Heres an example of what a YABN object would look like:
```
0x0D
0x04Byte Key0x000x01
0x0CArray With Trues0x000x020x020x020x00
0x00
```
JSON version of the above:
```json
{
  "Byte Key": 1,
  "Array With Trues": [true, true, true]
}
```

YABN Types:
| Type                     | Byte ID | Has Data |
|--------------------------|---------|----------|
| Null                     | 0x01    | False    |
| Boolean (True)           | 0x02    | False    |
| Boolean (False)          | 0x03    | False    |
| Byte (8 bit)             | 0x04    | True     |
| Short (16 bit)           | 0x05    | True     |
| Int (32 bit)             | 0x06    | True     |
| Long (64 bit)            | 0x07    | True     |
| Double (64 bit)          | 0x08    | True     |
| Float (32 bit)           | 0x09    | True     |
| CString                  | 0x0A    | True     |
| Empty String             | 0x0B    | False    |
| Array                    | 0x0C    | True     |
| Object                   | 0x0D    | True     |
| Empty Array              | 0x0E    | False    |
| Empty Object             | 0x0F    | False    |
| Null String              | 0x10    | True     |
| Typed Array              | 0x11    | True     |
| Dataless Typed Array     | 0x12    | True     |

I've put which ones have data or not and their byte id.

Examples:

```
0x0D

0x01Null Value 0x00 //has a key of 'Null Value ' with a value of null
0x02Boolean True 0x00 //has a key of 'Boolean True ' with a value of true
0x03Boolean False 0x00 //has a key of 'Boolean False ' with a value of false
0x04Byte of 4 0x000x04 //has a key of 'Byte of 4 ' with a value of 4
0x05Short of 256 0x00 0x000xff //has a key of 'Short of 256 ' with a value of 256
0x06Int of 256 0x00 0x000x000x000xff //has a key of 'Int of 256 0' with a value of 256
0x07Long of 256 0x00 0x000x000x000x000x000x000x000xff //has a key of 'Long of 256 ' with a value of null
0x08Double of some number 0x00 0x000x000x000x000x000x000x000xff //has a key of 'Double of some number ' with a value of something
0x09Float of some number 0x00 0x000x000x000xff //has a key of 'Float of some number ' with a value of something
0x0AString of something 0x00 This is a C style string0x00 //has a key of 'String of something ' with a value of ' This is a C style string'
0x0BEmpty String 0x00 //has a key of 'Empty String  with a value of ''
0x0CArray of 1 byte 0x00 0x040x010x00 //has a key of 'Array of 1 byte ' with a value of [1]
0x0DObject of 1 byte 0x00 0x04Key0x000x010x00 //has a key of 'Object of 1 byte ' with a value of {Key: 1}
0x0EEmpty Array 0x00 //has a key of 'Empty Array  with a value of []
0x0FEmpty Object 0x00 //has a key of 'Empty Object ' with a value of {}
0x10Nullable String 0x00 0x01String\u0000With\u0000Null\u0000Spacing0x00 //has a key of 'Nullable String ' with a value of 'String\u0000With\u0000Null\u0000Spacing'
0x11Typed Array 0x00 0x040x010x010x010x010x00  //has a key of 'Typed Array ' with a value of [1, 1, 1, 1]
0x12Dataless Typed Array 0x00 0x0B0x8 //has a key of 'Dataless Typed Array ' with a value of ["", "", "", "", "", "", "", ""]

0x00
```
