import * as React from "react";
import { LineChart, Line, Tooltip, XAxis, YAxis } from 'recharts';

const data = [{name: 'Page A', uv: 400, pv: 2400, amt: 2400},{name: 'Page A', uv: 300, pv: 2400, amt: 2400},{name: 'Page A', uv: 700, pv: 2400, amt: 2400}];

type ResponseTime = {
  timestamp: Date,
  responseTime: number
}

type HistoryData = {
  data: Array<ResponseTime>
}

const formatTimestampToDate = function(timestamp:Date) {
  return new Intl.DateTimeFormat("en-US", {
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit"
  }).format(new Date(timestamp))
}

class HealthHistory extends React.Component<HistoryData> {
  render() {
    return (
      <div>
        <LineChart width={1200} height={200} data={this.props.data}>
          <XAxis dataKey="timestamp" reversed tickFormatter={timeStr => formatTimestampToDate(timeStr)}/>
          <YAxis></YAxis>
          <Line type="monotone" dataKey="responseTime" stroke="#8884d8" />
          <Tooltip formatter={ (timeStr:number) => timeStr + " ms"}/>
        </LineChart>
      </div>
    );
  }
}

export default HealthHistory;