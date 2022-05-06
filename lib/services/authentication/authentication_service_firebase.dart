// @dart=2.9

import 'dart:async';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/foundation.dart';
import 'package:get/get.dart';
import 'package:flutter/material.dart';
import 'package:mygreenapp/app/app.dart';
import 'package:mygreenapp/services/navigation_service.dart';
import 'package:mygreenapp/ui/screen/home/home_screen.dart';

import '../../model/user.dart'
    as AppUser; // To resolve conflict with firebase 'User' class

import '../firebase.dart';
import 'authentication_service.dart';

class AuthenticationServiceFirebase extends AuthenticationService {
  final _firebaseAuth = FirebaseAuthentication();
  FirebaseAuth get _auth => _firebaseAuth.auth;
  final navigator = NavigationService();

  @override
  Future signIn(
      {@required String email,
      @required String password,
      Function(AppUser.User) onSuccess,
      Function(Exception) onError}) async {
    try {
      final UserCredential credential = await _auth.signInWithEmailAndPassword(
          email: email, password: password);
      final User user = credential?.user;

      if (user == null) return;
      print(_auth.currentUser.uid);
      onSuccess?.call(transformUserData(user));
    } on FirebaseAuthException catch (e) {
      if (e.code == 'wrong-password') {
      } else if (e.code == 'user-not-found') {}
      onError?.call(e);
    }
  }

  Future<void> showErrorDialog(BuildContext context, String text) {
    return showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            title: const Text('Error'),
            content: Text(text),
            actions: [
              TextButton(
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: const Text('OK'),
              )
            ],
          );
        });
  }

  Future<void> signOut(
      {Function onSuccess, Function(Exception) onError}) async {
    try {
      await _auth.signOut();
      onSuccess?.call();
    } on FirebaseAuthException catch (e) {
      onError?.call(e);
    }
  }

  // Commenting the following code out means, do not use auth stream from Firebase
  // @override
  // Stream get stream => _auth.authStateChanges();

  @override
  AppUser.User transformUserData(dynamic userData) {
    if (userData == null) return null;

    final User user = userData; // Firebase Auth User class

    return AppUser.User(
      // User data that are passed to Viewmodel is in the form our own User model class (not Firebase Auth user)
      username: user.email,
      email: user.email,
      name: user.displayName,
      uid: user.uid,
    );
  }
}
