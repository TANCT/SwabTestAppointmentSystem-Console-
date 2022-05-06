import 'package:flutter/material.dart';

class RegisterTextField extends StatefulWidget {
  TextEditingController? controller;
  final String? hintText, labelText;
  final String? validator;
  final String? error;
  final bool enableSuggestions, autoCorrect, obscureText;
  final TextInputType? keyboard;
  RegisterTextField(
      {Key? key,
      this.controller,
      this.hintText,
      this.labelText,
      this.autoCorrect = true,
      this.enableSuggestions = true,
      this.obscureText = false,
      this.keyboard,
      this.validator,
      this.error})
      : super(key: key);

  @override
  State<RegisterTextField> createState() => _RegisterTextFieldState();
}

class _RegisterTextFieldState extends State<RegisterTextField> {
  var _text = '';
  TextInputType? key;
  get controller => widget.controller;

  bool get obscureText {
    if (widget.labelText == "Password: ") {
      return true;
    } else {
      return false;
    }
  }

  get keyboard => key;

  @override
  Widget build(BuildContext context) {
    return Container(
      child: TextFormField(
        controller: widget.controller,
        obscureText: obscureText,
        keyboardType: keyboard,
        decoration: InputDecoration(
            hintText: widget.hintText,
            hintStyle: TextStyle(color: Colors.grey, fontSize: 12),
            labelText: widget.labelText,
            errorText: error,
            labelStyle: TextStyle(color: Colors.black, fontSize: 12)),
        onChanged: (text) => setState(() => ""),
      ),
    );
  }

  String? get error {
    final String? text = widget.controller?.text;

    if (text == "") {
      return 'This field is required.';
    }
    if (widget.labelText == "Email: ") {
      String pattern = r'\w+@\w+\.\w+';
      RegExp regex = RegExp(pattern);
      String match = text!;
      if (!regex.hasMatch(match)) {
        return 'Invalid email format';
      }
    }
    if (widget.labelText == "Password: " && text != null) {
      if (text.length < 8) {
        return 'Too short';
      }
    }
    if (widget.labelText == "Phone Number: ") {
      String pattern =
          r'^(\+?6?01)[02-46-9]-*[0-9]{7}|^(\+?6?01)[1]-*[0-9]{8}$';
      RegExp regex = RegExp(pattern);
      String match = text!;
      if (!regex.hasMatch(match)) {
        return 'Invalid phone number format';
      }
    }
    return null;
  }
}
