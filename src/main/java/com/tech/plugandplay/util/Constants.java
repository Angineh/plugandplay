package com.tech.plugandplay.util;

public class Constants {
	public interface SLPackages {
		int PAGE_SIZE = 50;
		int BM_PACKAGE_ID = 251;
		int VM_PACKAGE_ID = 46;
		int BM_HOURLY_PACKAGE_ID = 200;
	}
	public interface HourlyBMPriceIds {
		// How to select PRESET_ID programtically
		long PRESET_ID = 64;
	}
	public interface InstanceType {
		String VIRTUAL = "virtual";
		String BAREMETAL = "bareMetal";
		String VM = "VM";
		String BM = "BM";
		int ORDER_SIZE = 1000;
	}
	public interface Config {
		String SMALL = "small";
		String MEDIUM = "medium";
		String LARGE = "large";
	}

	public interface BillingType {
		String HOURLY = "hourly";
		String MONTHLY = "monthly";
	}
	public interface HardwareStrings {
		// Small Configuration CPU cores=10, RAM=64GB
		// Medium Configuration CPU cores=10, RAM=128GB
		// Medium Configuration CPU cores=12, RAM=256GB
		String SERIES = "Dual"; // Dual Intel Xeon E5-2650 v3, we will only match Dual
		String CPU_LARGE = "12 Cores"; // For Large Configuration
		String CPU_MEDIUM_SMALL = "10 Cores"; // For Small/Medium Configuration
		
		// "locationGroupId":null, means available on all data centers
		String LOCATION_GROUP_ID = "\"locationGroupId\":null";
	}
	
	public interface PackageSearchString {
		String VM_PACKAGE_STR = "Virtual Server";
		// description from HW: Dual Intel Xeon E5-2690 v3 (12 Cores, 2.60 GHz)
		// package description: Dual E5-2600 v3 Series (12 Drives)
		String BM_PACKAGE_DUAL = "Dual";
		String BM_PACKAGE_E5 = "E5-26"; // generic as E5-2650 is for S/M and ES-2690 is for Large
		String BM_PACKAGE_DRIVES = "12 Drives"; 
		String BM_PACKAGE_SERIES = "Series";
		String BM_PACKAGE_V3 = "v3";
	}
	
	public interface BMPlaceOrderConstants {
		String COMPLEX_TYPE = "SoftLayer_Container_Product_Order_Hardware_Server";
		int partitionTemplateId = 639;
	}
	
	public interface RaidArrayTypeID {
		long RAID0 = 1;
		long RAID1 = 2;
		long RAID5 = 3;
		long RAID6 = 4;
		long RAID10 = 5;
		long JBOD   = 9; // just a bunch of disks
	}
	
	public interface DataCenterShortNames {
		String DEFAULT = "dal09";
		String TORONTO1 = "tor01";
		String DALLAS05 = "dal05";
		String DALLAS09 = "dal09";
		String LONDON2 = "lon02";
		String AMSTERDAM3 = "lon02";
		String SYDNEY1 = "lon02";
		String WASHINGTON_DC4 = "wdc04";
		String PARIS1 = "par01";
	}
	
	public interface DataCenterLongNames {
		String DEFAULT = "Dallas 9";
		String TORONTO1 = "Toronto 1";
		String DALLAS05 = "Dallas 5";
		String DALLAS09 = "Dallas 9";
		String LONDON2 = "London 2";
		String AMSTERDAM3 = "Amsterdam 3";
		String SYDNEY1 = "Sydney 1";
		String WASHINGTON_DC4 = "Washington, DC 4";
		String PARIS1 = "Paris 1";
		//sjc03 San Jose 3
		String SANJOSE03 = "San JOSE 3";
	}
	
	public interface HourlyBMDataCenter {
		String DEFAULT = "DALLAS";
		String TORONTO = "TORONTO";
		String DALLAS = "DALLAS";
		String LONDON = "LONDON";
		String AMSTERDAM = "AMSTERDAM";
		String SYDNEY = "SYDNEY";
		String WASHINGTON = "WASHINGTON";
		String PARIS = "PARIS";
	}

	public interface BMRequiredCategoryIds {
		long CORES          =   1;
		long RAM            =   3;
		long DISK         	=   4;
		long BANDWIDTH      =  10;
		long RAID           =  11;
		long OS             =  12;
		long PRIMARY_IP     =  13;
		long HOST_PING      =  20;
		long NOTIFICATION   =  21;
		long RESPONSE       =  22;
		long NETWORK        =  26;
		long VPN            =  31;
		long VULNERABILITY  =  32;
		long POWER_SUPPLY   =  35;
		long REMOTE         =  46;
	}
	
	public interface VMRequiredCategoryIds {
		long RAM            =  3;
		long BANDWIDTH      =  10;
		long OS             =  12;
		long PRIMARY_IP     =  13;
		long HOST_PING      =  20;
		long EMAIL_TICKET   =  21;
		long NOTIFICATION   =  22;
		long NETWORK        =  26;
		long VPN            =  31;
		long VULNERABILITY  =  32;
		long REMOTE         =  46;
		long CORES          =  80;
		long FIRST_DISK     =  81;
		long SECOND_DISK    =  116;
	}
	
	public interface PartitionTemplateIds {
		long CDS_PARTITION_TEMPLATE_ID = 639;
	}
	
	public interface REGEXGeneral {
		String WILD_CHAR			= "(.*)";
		String STARTS_WITH			= "^";
	}
	
	public interface REGEXForVMDescription {
		String OS_S_M_L         	= "(.*)Minimal Install(.*)64 bit(.*)";
		String PRIMARY_IP_S_M_L     = "(^1 IP Address$)";
		String HOST_PING_S_M_L      = "(^Host Ping$)";
		String EMAIL_TICKET_S_M_L   = "(^Email and Ticket$)";
		String NOTIFICATION_S_M_L   = "(^Automated Notification$)";
		String VPN_S_M_L	        = "(^Unlimited SSL VPN Users & 1 PPTP VPN User per account$)";
		String VULNERABILITY_S_M_L  = "(^Nessus Vulnerability Assessment & Reporting$)";
		String POWER_SUPPLY_S_M_L	= "(.*)Power Supply";
		String REMOTE_S_M_L         = "Reboot / Remote Console";
	}
	
	public interface REGEXForBMDescription {
		String CORES_SMALL 			= "(^20 Cores(.*).*(.*)Xeon E5-2650)(.*).*(.*)";
		String CORES_MEDIUM 		= "(^20 Cores(.*).*(.*)Xeon E5-2650)(.*).*(.*)";
		String CORES_LARGE			= "(^20 Cores(.*).*(.*)Xeon E5-2690)(.*).*(.*)";
		
		String DISK_4TB       		= "(^4.00 TB SATA.*)";
		String DISK_3TB       		= "(^3.00 TB SATA.*)";
		String DISK_2TB        		= "(^2.00 TB SATA.*)";
		String DISK_1TB        		= "(^1.00 TB SATA.*)";
		
		String RAM_SMALL        	= "(^64 GB(.*))";
		String RAM_MEDIUM       	= "(^128 GB(.*))";
		String RAM_LARGE        	= "(^256 GB(.*))";
		
		String BANDWIDTH_MASTER_S_M_L  	= "((.*)GB Bandwidth$)";
		String BANDWIDTH_DATA_S_M_L	    = "(0(.*)GB Bandwidth$)";
		String RAID_S_M_L       	= "(^RAID$)";
		String OS_S_M_L         	= "(^Red Hat Enterprise Linux 6.x(.*)64 bit(.*)per-processor licensing(.*)$)";
		String PRIMARY_IP_S_M_L     = "(^1 IP Address$)";
		String HOST_PING_S_M_L      = "(^Host Ping$)";
		String NOTIFICATION_S_M_L   = "(^Email and Ticket$)";
		String RESPONSE_S_M_L       = "(^Automated Notification$)";
		String NETWORK_DATA_S_M_L 	= "(^10 Gbps Dual Private Network Uplinks(.*)Unbonded(.*)$)";
		String NETWORK_MASTER_S_M_L = "(^10 Gbps Dual Public & Private Network Uplinks(.*)Unbonded(.*)$)";
		String VPN_S_M_L	        = "(^Unlimited SSL VPN Users & 1 PPTP VPN User per account$)";
		String VULNERABILITY_S_M_L  = "(^Nessus Vulnerability Assessment & Reporting$)";
		String POWER_SUPPLY_S_M_L	= "(.*)Power Supply";
		String REMOTE_S_M_L         = "(^Reboot / KVM over IP$)";
		String BM_REGEX             = "";
	}
	
	public interface Actions {
		String CREATE = "create";
		String RELOAD = "reload";
		String RENAME = "rename";
		String DELETE = "delete";
	}
	
	public interface DB {
		boolean USE_DB2 = true; 
		String TABLE_NAME = "TABLE_NAME";
		String DB_URL = "DB_URL";
		String USER = "user";
		String PASSWORD = "password";
		String DB_USER = "DB_USER";
		String DB_PASSWORD = "DB_PASSWORD";
		String DB2_DRIVER = "DB2_DRIVER";
		String DB2_TABLESPACE = "DB2_TABLESPACE";
		String DERBY_DRIVER = "DERBY_DRIVER";
		String DERBY_URL= "DERBY_URL";
		String SQL_CREATE_TABLE = "SQL_CREATE_TABLE";
	}
	
	public interface GenericErrorMessages {
		String EXCEEDED_SIZE = "The top 100 list already has one hundred entries.";
		String INPUT_JSON = "Input JSON is NULL or Empty.";
		String CURRENT_PROVIDER = "Only provider 'fyre' is currently supported.";
		String CREDENTIALS_REQUIRED = "Both user and credentials are required to place an order.";
		String JSON_PARSE_ERROR = "Error in parsing the Input JSON - invalid parameters or doesn't adhere to the required JSON.";
		String MISSING_MANDATORY_PARAMS = "Input JSON is Missing mandatory parameters, which are required to place an order.";
		String EXCEPTION_THROWN = "Exception thrown. Check logs for Errors.";
		String FAILED_EXCEPTION = "Failed creating/reloading/deleting. Check logs for failures.";
		String MACHINE_SPECS_ID_UNEXPECTED = "Unexpected machineSpecId. machineSpecId should not exists.";
		String MACHINE_SPECS_UNEXPECTED = "Unexpected machineSpec. machineSpecs shouldn't exists.";
		String EXPECTED_NODE_IDS = "node-id/node-ids are required to delete a node.";
		String EXPECTED_REQUEST_ID = "To maintain consistency, Request Id is excepted while deleting nodes using nodeIds.";
		String REQUEST_ID_REQUIRED = "Request Id is required.";
		String NODE_IDS_EXIST_FAILURE = "Node ID not found in DB.";
		String STATUS_EMPTY = "Cluster status is Empty. Status cannot be null";
		String WRONG_ACTION = "Wrong cluster Action. Rename status should be picked up from database.";
		// There are insufficient resources behind router bcr04a.dal09 to fulfill the request for the following 
		// guests:xxxx (code: SoftLayer_Exception_Virtual_Host_Pool_InsufficientResources, status: 500)
		String INSUFFICENT_RESOURCES_STR = "insufficient resources";
		String INSUFFICENT_RESOURCES_NODE = "There are insufficient resources to fulfill the request for : ";
	}
	
	public interface GenericDebugMessages {
		String EXISTS_IN_DB = "Retrieved Clusters info from DB.";
		String EXISTS_IN_CLUSTERS_LIST = "Retrieved from clusters list.";
		String REQUEST_ID_EXISTS = "RequestID exists in DB;"; 
		String RETURN_STATUS = "Returning with status : ";
	}
	
	public interface ClusterStatus {
		// Generic status
		String API_EXCEPTION = "Received com.softlayer.api.ApiException$Internal exception.";
		String NODES_WRONG_ARGS = "Input Arguments are null or invalid.";
		String NODES_EXISTS_ERROR = "Either the nodes are not created or it is already deleted.";
		String DUPLICATE_REQUEST_ID = "Request ID is duplicate. JSON sent doesn't match.";
		String MISSING_MACHINE_SPEC = "Machine spec is missing for the hostname : ";
		String INSUFFICENT_RESOURCES_CLUSTER = "Some or all nodes could not be acquired due to insufficient resources.";
		
		// Errors for checkCreateCluster
		String STATUS_RETRIEVE_ERROR = "Cannot Retrieve any information from the Device.";
		String HOSTNAME_ERROR = "SoftLayer account has no machine with the requested hostname : ";
		
		// Create nodes
		String CREATE_NODES_RECEIVED = "Received Request for Create Nodes.";
		String CREATE_NODES_FAILED = "Creating Nodes Failed.";
		String CREATING_NODES = "Acquiring Hardware/Devices.";
        String CREATED_NODES = "Nodes Created Successfully.";
        
        // Reload Nodes
        String RELOAD_NODES_RECEIVED = "Received Request for Reload Nodes.";
        String RELOAD_NODES_FAILED = "Reload Nodes Failed.";
        String RELOADING_NODES = "Reloading Devices.";
        String RELOADED_NODES = "Reloaded Successfully.";
        
        // Rename Nodes
        String RENAME_NODES_RECEIVED = "Received Request for Renaming Nodes.";
        String RENAME_NODES_FAILED = "Rename Nodes Failed.";
        String RENAMING_NODES = "Renaming Devices.";
        String RENAMED_NODES = "Renamed Successfully.";
        
        // Delete Nodes
        String DELETE_NODES_RECEIVED = "Received Request for Deleting Nodes.";
        String DELETE_NODES_FAILED = "Delete Nodes Failed.";
        String DELETING_NODES = "Deleting Devices.";
        String DELETED_NODES = "Deleted Successfully."; 
	}
	
	public interface HTTPCodes {
		int OK = 200;
		int ACCEPTED = 202;
		int CREATED = 201;
		int BAD_REQUEST = 400;
		int FORBIDDEN = 403;
		int UNAUTHORIZED = 401;
	}
	
	public interface NodeStatus {
		
		String ACTIVE = "Active";
		String PENDING = "Pending";
		String ERROR = "Error";
		String DELETED = "Deleted";
				
	}
}
