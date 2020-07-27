import * as React from "react";
import Button from '@material-ui/core/Button';

type Service = {
  name: string,
  status: string
}

class Health extends React.Component<Service> {
  render() {
    return (
      <div>
        <p>{this.props.name}</p>
        <Button variant="contained" color="primary">{this.props.status}</Button>
      </div>
    );
  }
}

export default Health;