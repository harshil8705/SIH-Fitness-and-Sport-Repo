import React from "react";
//import ExerciseCard from "./exerciseCard";
import AskAi from "./askAi";
export default function MainSection() {
  return (
    <main className="flex-1  bg-gray-100">
      {/* Title */}
      <h2 className="text-2xl text-white font-semibold mb-6 bg-sky-900 w-100vh h-15 text-center" >Today's Goal</h2>

    <div>
        {/* <ExerciseCard/> */}
        <AskAi/>

    </div>
      
   
    </main>
  );
}
