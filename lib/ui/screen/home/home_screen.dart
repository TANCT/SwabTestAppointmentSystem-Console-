import 'package:flutter/material.dart';
import 'package:mygreenapp/ui/screen/home/home_navBar.dart';
import 'package:mygreenapp/ui/screen/home/home_screenState.dart';
import 'package:mygreenapp/ui/screen/map/mapScreen.dart';



import 'home.dart';

class HomeScreen extends StatelessWidget {
  static Route route() => MaterialPageRoute(builder: (_) => HomeScreen());
  const HomeScreen({Key? key}) : super(key: key);
  static int currIndex=0;
 static final pages = [
	 HomeScreenful(),
   MapScreen(),
	 HomeScreenful(),
	MapScreen(),
  MapScreen(),
  MapScreen()
  
];
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: const HomeAppBar(),
       body:pages[3],
        //body: HomeScreenful(),
        floatingActionButton: FloatingActionButton(
        onPressed:(){
          Navigator.of(context).push(MaterialPageRoute(builder:(context)=>MapScreen(),));
        },
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
        bottomNavigationBar: HomeNavigationBar(),
      ),
    );
  }
}
