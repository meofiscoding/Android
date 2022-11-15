import 'package:flutter/material.dart';

class EditText extends StatelessWidget {
  const EditText({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      autofocus: true,
      //align
      decoration: InputDecoration(
        filled: true,
        fillColor: Colors.white,
        focusedBorder: OutlineInputBorder(
          borderSide:
          BorderSide(width: 1, color: Colors.pink[800]!),
          borderRadius: BorderRadius.circular(10),
        ),
        //add border
        enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(10),
            //add border color
            borderSide:
            BorderSide(width: 1, color: Colors.pink[800]!)),
        isDense: true,
        // Added this
        contentPadding: const EdgeInsets.all(8),
      ),
      validator: (value) {
        if (value == null || value.isEmpty) {
          return 'Please enter some text';
        }
        return null;
      },
    );
  }
}