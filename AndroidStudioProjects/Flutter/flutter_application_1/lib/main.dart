import 'package:flutter/material.dart';
import 'package:flutter_application_1/TextField.dart';

import 'Label.dart';

//Entry point
void main() {
  //take a simple widget to inflate to screen
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: MyForm(),
      // debugShowCheckedModeBanner: false,
    );
  }
}

//Form
class MyForm extends StatefulWidget {
  const MyForm({super.key});

  //constructor with lambda
  @override
  State<MyForm> createState() => _MyFormState();
}

//Def corresponding state class
//This class hold data related to form
class _MyFormState extends State<MyForm> {
  // Define the focus node. To manage the lifecycle, create the FocusNode in
  // the initState method, and clean it up in the dispose method.
  late FocusNode focusNode;

  // Create a global key that uniquely identifies the Form widget
  // and allows validation of the form.
  //
  // Note: This is a `GlobalKey<FormState>`,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();

  @override
  void initState() {
    super.initState();
    focusNode = FocusNode();
  }

  @override
  void dispose() {
    focusNode.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        //make text tittle bold
        title: const Text("三凛に質問する"),
        //change color
        backgroundColor: Colors.pink[200],
        //make text center
        centerTitle: true,
      ),
      body: Container(
          //add color to container
          color: Colors.pink[50],
          //change padding left and right is 30, top and bottom const  is 20
          padding: const EdgeInsets.fromLTRB(30, 20, 30, 20),
          //make child column
          child: SingleChildScrollView(
            child: Form(
              key: _formKey,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                //make column center
                children: [
                  Text(
                      "*ご注意\nこちらの質問フォームはコンテンツに関するご質問専用です。\n アプリの機能に関する質問はこちらではなく\n【アプリについてご質問・ご要望】へお問合せください。",
                      style: TextStyle(color: Colors.red[600], fontSize: 10.7)),
                  const SizedBox(height: 30),
                  //add text
                  Label(title: "お名前"),
                  const SizedBox(height: 5),
                  //add text
                  const EditText(),
                  const SizedBox(height: 20),
                  //add text
                  Label(title: "メールアドレス"),
                  const SizedBox(height: 5),
                  //add text
                  const EditText(),
                  const SizedBox(height: 20),
                  //add text
                  Label(title: "メールアドレス確認用"),
                  const SizedBox(height: 5),
                  //add text
                  const EditText(),
                  const SizedBox(height: 20),
                  //add text
                  Label(title: "ご質問　(具体的に記載をお願いします)"),
                  const SizedBox(height: 5),
                  //add text area
                  TextField(
                    autofocus: true,
                    maxLines: 5,
                    decoration: InputDecoration(
                      filled: true,
                      fillColor: Colors.white,
                      focusedBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(10),
                          //add border color
                          borderSide:
                          BorderSide(width: 1, color: Colors.pink[800]!)),
                      //add border
                      enabledBorder: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(10),
                          //add border color
                          borderSide:
                          BorderSide(width: 1, color: Colors.pink[800]!)),
                    ),
                  ),
                  const SizedBox(height: 30),
                  //nest column
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      ElevatedButton(
                        onPressed: () {
                          if (_formKey.currentState!.validate()) {
                            // If the form is valid, display a Snackbar.
                            ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(content: Text('Processing Data')));
                          }
                        },
                        style: ElevatedButton.styleFrom(
                          primary: Colors.pink[300],
                          onPrimary: Colors.white,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(50),
                          ),
                        ),
                        child: const Text("送信する"),
                      ),
                      //add text
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      //add text with text color is pink[600]
                      Text(
                        "ご質問を承りました。",
                        style: TextStyle(color: Colors.pink[600]),
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  const Text(
                      "<メモ>\n・送信完了後、送信完了しましたの文字を表示させる\n ・このフォームは三凛に直接届くようにする\n・アプリ内にメールボックスを持たずEメールで返信",
                      style: TextStyle(fontSize: 10.7)),
                ],
              ),
            )

          )
          //add text child with color is red and text size is 10]
          ),
    );
  }
}
