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
