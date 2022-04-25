import React from 'react';
import { withStyles, Theme, createStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import CheckIcon from '@material-ui/icons/Check';
import ErrorOutlineOutlinedIcon from '@material-ui/icons/ErrorOutlineOutlined';
import HelpOutlineOutlinedIcon from '@material-ui/icons/HelpOutlineOutlined';
import HistoryIcon from '@material-ui/icons/History';
import Paper from '@material-ui/core/Paper';
import HealthHistory from './HealthHistory';

const StyledTableCell = withStyles((theme: Theme) =>
  createStyles({
    head: {
      backgroundColor: "#00AE2E",
      color: theme.palette.common.white,
      fontSize: 19,
    },
    body: {
      fontSize: 16,
    },
    svg: {
      marginBottom: "-5px",
    },
  }),
)(TableCell);

const useStyles = makeStyles({
  table: {
    minWidth: 700,
  },
});

const StyledTableRow = withStyles((theme: Theme) =>
  createStyles({
    root: {
      '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
      },
      fontSize: 14,
    },
  }),
)(TableRow);

type HealthList = {
  list: Array<Health>
}

type ResponseTime = {
  timestamp: Date,
  responseTime: number
}

type Health = {
  name: string,
  status: string,
  responsesTimeInMillis: Array<ResponseTime>,
  timestamp: Date
}

type HealthRowProps = {
  rowValue: Health,
  rowKey: number
}

class HealthRow extends React.Component<HealthRowProps> {
  state = { open: false}

  render() {
      const { rowValue, rowKey } = this.props;
      const { open } = this.state;

      return (
      <>
        <StyledTableRow key={rowKey}>
          <>
          <StyledTableCell>
            
            {rowValue.name}
          </StyledTableCell>
          <StyledTableCell align="right">{new Intl.DateTimeFormat("en-US", {
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit"
          }).format(new Date(rowValue.timestamp))}</StyledTableCell>
          <StyledTableCell align="right">
            {rowValue.responsesTimeInMillis.length > 0 && rowValue.responsesTimeInMillis[0].responseTime > 0 ? rowValue.responsesTimeInMillis[0].responseTime : '---'} ms <HistoryIcon onClick={() => this.setState(() => ({ open: !open }))}></HistoryIcon>
            </StyledTableCell>
          <StyledTableCell align="right">{
            rowValue.status === "UP" ? <CheckIcon></CheckIcon>
              : rowValue.status === "DOWN" ? <ErrorOutlineOutlinedIcon></ErrorOutlineOutlinedIcon>
                : <HelpOutlineOutlinedIcon></HelpOutlineOutlinedIcon>
          }</StyledTableCell>
          </>
        </StyledTableRow>
        { open ? 
        <StyledTableRow>
          <TableCell colSpan={4} align="center">
              <HealthHistory data={rowValue.responsesTimeInMillis}></HealthHistory>
          </TableCell>
        </StyledTableRow>
        : null }
      </>
      );
  }
}

export default function HealthTables(healthList: HealthList) {
  const classes = useStyles();

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Service Name</StyledTableCell>
            <StyledTableCell align="right">Timestamp</StyledTableCell>
            <StyledTableCell align="right">Response Time</StyledTableCell>
            <StyledTableCell align="right">Status</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          <>
          {console.log(healthList.list)}
          {healthList.list.map((row, key) => (
            <>
            <HealthRow rowValue={row} rowKey={key} />
            </>
          ))}
          </>
        </TableBody>
      </Table>
    </TableContainer>
  );
}
