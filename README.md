# CS445-FinalProject
Final Project yall.

Project Members:
 - Sofia Barraza
 - Shaylyn Wetts
 - Christopher Sanchez
 - Mario Garcia
  
# Architecture
RenderEngine handles all of the transformations for our Mesh objects, so there is minimal need to call glRotatef, glScalef or glTranslatef, since
those will be handled by the engine. Mesh is an abstract class with a transform, meaning it can be extended to be anything else, like a line or sphere, 
or a cube. In this case our object Cube is extending Mesh. Any objects extending Mesh can therefore be pushed into the RenderEngine object for drawing. 
  
The process of our rendering loop goes as this:
```Python
create engine
create camera

initialize engine
start engine

engine gets camera reference

while engine is still running
  Update the time
  Update the engine
  Push our meshes into the engine
  call render() from our engine. This will tell our engine to start drawing our meshes.
  clear our the engine of these pushed meshes.
repeat
```
It is not a super clean algorithm, as it can be broken down even further for more power, but this will suffice enough for our project!

# Basic Physics Engine Dev
There is a physics engine implementation written, it is rather basic and not optimized. It runs on a separate thread and performs O(n)
time (which is normal), but if colliders are present, the time jumps to O(n^2) (horrid). If up to the task, implementing a sweep and 
prune technique can help HUGELY with the physics calculations.

# To Contribute
Simply git clone this repository, this will create a git clone to the remote repository.

# To Build
This project runs on netbeans, so there is already a netbeans project file inside. All you have to do is go into your netbeans IDE, Open Project, and
locate the folder CS445-FinalProject in this repo. Everything should be set and ready to go. If you wish to build the project yourself, without all
of the crazy netbeans stuff, let me know and I'll change it up.
