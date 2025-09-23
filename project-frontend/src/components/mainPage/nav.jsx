import React from "react";
import { useState } from "react";
import AiSidebar from "./aibutton"
export default function NavBar() {
    const [open, setOpen] = useState(false);// useState button
  return (
    <div>
      <nav className="bg-gray-800 p-4 text-white flex gap-10">
        <a href="/" className="font-bold text-lg  hover:opacity-75">
          FITNESS TRACKER
        </a>
        <a href="" className="font-semibold text-md hover:drop-shadow-lg transition opacity-75">Home</a>

        <a href="" className="font-semibold text-md hover:drop-shadow-lg transition opacity-75">About us</a>

        <button onClick={() => setOpen(true)}
        className="ml-auto text-white px-3 py-1 rounded shadow-sm hover:opacity-75">
            <i className="fa-solid fa-wand-magic-sparkles text-white "></i>
        </button>
      </nav>
       <AiSidebar isOpen={open} onClose={() => setOpen(false)} />
    </div>
  );
}
