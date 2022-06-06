import 'package:flutter/material.dart';
import 'package:my_green_app/model/RecycleCenter.dart';
import 'package:my_green_app/ui/views/home/home_screenState.dart';
import 'package:my_green_app/ui/views/home/home_viewmodel.dart';
import 'package:my_green_app/ui/views/recyclecenter/recyclecenter_screenstate.dart';
import 'package:my_green_app/ui/views/recyclecenter/recyclecenter_viewmodel.dart';
import 'package:my_green_app/constants/routes_path.dart' as routes;


class RCListButton extends StatelessWidget {
  final RecycleCenterViewmodel viewmodel;
  final RecycleCenterScreenfulState state;
  final RecycleCenter model;
  const RCListButton(
      {required this.viewmodel, required this.state, required this.model});

  @override
  Widget build(BuildContext context) {
    return Container(
        child: Row(children: <Widget>[
      ElevatedButton.icon(
        icon: Icon(
          Icons.map_outlined,
          size: 18.0,
        ),
        label: Text('Map'),
        onPressed: () async {
          state.onView(viewmodel, model.email);
          Navigator.of(context).pushNamed(routes.rcRoute);        },
      ),
      SizedBox(
        width: 8,
      ),
      ElevatedButton.icon(
        icon: Icon(
          Icons.edit,
          size: 18.0,
        ),
        label: Text('Edit'),
        onPressed: () async {},
      ),
      SizedBox(
        width: 8,
      ),
      ElevatedButton.icon(
        icon: Icon(
          Icons.delete,
          size: 18.0,
        ),
        label: Text('Delete'),
        onPressed: () async {
          state.onDelete(viewmodel, model.email);
        },
      ),
    ]));
  }
}
