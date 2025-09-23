import React from "react";

export default function Sidebar() {

    

  return (
    <aside className="w-100 h-screen bg-gray-900 text-white flex flex-col">
      {/* Sidebar Header */}
      <div className="p-4 text-xl font-bold border-b border-gray-700">
        profile
      </div>

        <div className="flex flex-col items-center p-4 border-b border-gray-700">
            {/* Cartoon Avatar */}
            <img
            src ="https://thumbs.dreamstime.com/z/pixar-style-d-male-character-humorous-caricature-man-jeans-d-cartoon-character-realistic-hyper-detailed-rendering-302896321.jpg?ct=jpeg"
            alt="User Avatar"
            className="w-65 h-100  object-cover overflow-hidden"
            />

            {/* User Details */}
            <div className="mt-6 text-center">
            <h3 className="text-lg font-semibold">John Doe</h3>
            <p className="text-lg text-gray-400">Height: 5'9"</p>
            <p className="text-lg text-gray-400">Weight: 70kg</p>
            <p className="text-lg text-gray-400">Age: 25</p>
            </div>
        </div>
    </aside>
  );
}
