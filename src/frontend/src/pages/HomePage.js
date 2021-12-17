import { React, useEffect, useState } from 'react';
import './HomePage.scss';
import { TeamTile } from '../components/TeamTile'

export const HomePage = () => {

  const [teams, setTeams] = useState([]);

  useEffect(
    () => {
      const fetchAllTeams = async () => {
        const response = await fetch(`http://localhost:8080/teams`);
        const data = await response.json();
        console.log(data);
        setTeams(data);

      };

      fetchAllTeams();
    }, []

  );

  return (
    <div className="HomePage">
        <div className="header-section">
            <h1 className="app-name">IPL Dashboard Demo</h1>
        </div>
        <div classname="team-grid">
          {teams.map(team => <TeamTile key={team.id} teamName={team.teamName} />)}
        </div>
    </div>
  );
}


