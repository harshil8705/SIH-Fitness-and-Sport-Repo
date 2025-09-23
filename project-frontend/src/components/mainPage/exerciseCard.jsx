import React from "react";

export default function ExerciseCard({ image, name, benefit }) {
  // Default exercise image (fallback)
const defaultImage =
  "https://images.pexels.com/photos/414029/pexels-photo-414029.jpeg?auto=compress&cs=tinysrgb&h=350";
  const defaultName = "Push-ups";
  const defaultBenifit = "Strengthens chest, shoulders, and triceps"

  return (
    <div className="bg-white shadow-md rounded-xl w-60 p-4 ml-3">
      {/* Exercise Image */}
        <img
            src={image || defaultImage}
            alt={name}
            className="w-60 h-40 object-cover rounded-lg"
        />

      {/* Exercise Details */}
      <div className="mt-3">
        <h3 className="text-lg font-semibold text-gray-800">{name || defaultName}</h3>
        <p className="text-sm text-gray-600 mt-1">{benefit || defaultBenifit}</p>
      </div>
    </div>
  );
}
