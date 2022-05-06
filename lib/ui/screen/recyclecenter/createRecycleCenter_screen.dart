import 'package:flutter/material.dart';
import 'package:mygreenapp/ui/screen/recyclecenter/createRecycleCenter_body.dart';
import 'package:mygreenapp/ui/screen/recyclecenter/createRecycleCenter_view_model.dart';
import '../view.dart';


class CreateRecycleCenterScreen extends StatefulWidget {
  const CreateRecycleCenterScreen({ Key? key,required name }) : super(key: key);

  @override
  State<CreateRecycleCenterScreen> createState() => _CreateRecycleCenterScreenState();
}

class _CreateRecycleCenterScreenState extends State<CreateRecycleCenterScreen> {
  final nameController = TextEditingController();
  final qtyController=TextEditingController();
  final catController=TextEditingController();
  final methodController=TextEditingController();
  final timeController=TextEditingController();
  final descController=TextEditingController();
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: const CreateRecycleCenterBody(),
    );
  }
}

// class AppointmentScreen extends StatelessWidget {
//   const AppointmentScreen({ Key? key,required name }) : super(key: key);

//   @override
//   Widget build(BuildContext context) {
//     return View<AppointmentViewModel>(
//        builder: (context, viewmodel, _) => Scaffold(
//          body: Container(
//                 width: MediaQuery.of(context).size.width,
//                 height: MediaQuery.of(context).size.height,
//                 decoration: BoxDecoration(
//                   gradient: LinearGradient(
//                       begin: Alignment.topLeft,
//                       end: Alignment.bottomRight,
//                       colors: [
//                         Color.fromRGBO(209, 239, 181, 100),
//                         Color.fromRGBO(211, 250, 214, 100)
//                       ]),
//                 ),
//                 child:Text("dsjf"),
//        )
      
//     ));
//   }
// }