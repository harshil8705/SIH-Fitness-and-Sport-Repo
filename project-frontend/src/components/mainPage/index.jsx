import React from "react";
import NavBar from "./nav";
import Sidebar from "./sideBar";
import MainSection from "./mainSection";

export default function Home() {
  return (
    <div className="flex flex-col h-screen">
      {/* Navbar at top */}
      <NavBar />

      {/* Sidebar + Main Section side by side */}
      <div className="flex flex-1">
        <Sidebar />
        <MainSection />
      </div>
    </div>
  );
}
