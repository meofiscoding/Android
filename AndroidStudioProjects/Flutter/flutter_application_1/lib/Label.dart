import 'package:flutter/material.dart';

class Label extends StatelessWidget {
  String title;

  Label({required this.title});

  @override
  Widget build(BuildContext context) {
    return Text(
      title,
      //text align left
      textAlign: TextAlign.left,
      style: const TextStyle(
        fontSize: 13,
      ),
    );
  }
}
