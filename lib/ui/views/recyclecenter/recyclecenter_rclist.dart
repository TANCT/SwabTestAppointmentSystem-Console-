import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:my_green_app/ui/views/recyclecenter/recyclecenter_button.dart';
import 'package:my_green_app/ui/views/recyclecenter/recyclecenter_screenstate.dart'
    as state;

import '../../../model/RecycleCenter.dart';
import 'package:my_green_app/ui/views/recyclecenter/recyclecenter_viewmodel.dart'
    as rcviewmodel;

Widget buildRC(RecycleCenter rc) => ListTile(
      leading: CircleAvatar(child: Text(rc.name)),
      title: Text(rc.name),
      subtitle: Column(
        children: <Widget>[
          Text(rc.email),
          Text(rc.phone),
          Text(rc.address),
          Container(child: Row()),
          RCListButton(
              viewmodel: rcviewmodel.RecycleCenterViewmodel(),
              state: state.RecycleCenterScreenfulState(),
              model: rc)
        ],
      ),
    );
